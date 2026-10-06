// PROTOTYPE REGISTRY DESIGN PATTERN EXAMPLE 1

package designPatterns;

import java.util.HashMap;
import java.util.Map;

// ------------------------------------------------------------
// 1. PROTOTYPE INTERFACE
// ------------------------------------------------------------
// Every prototype must know how to create a copy of itself.
//
// T is the type that will be returned by clone().
//
// Example:
// Prototype<VMInstance>
// means clone() returns a VMInstance.
// ------------------------------------------------------------
interface Prototype<T> {
    T clone();
}


// ------------------------------------------------------------
// 2. BASE VM INSTANCE
// ------------------------------------------------------------
// This is our base prototype.
//
// A VMInstance contains all the common configuration that
// different VM types may have.
//
// Instead of creating every VM from scratch, we can create
// one configured VM and clone it whenever we need another one.
// ------------------------------------------------------------
public class VMInstance implements Prototype<VMInstance> {

    private String os;
    private String runtime;
    private boolean monitoringAgent;
    private boolean securityPatches;
    private String hostName;
    private String ipAddress;


    // --------------------------------------------------------
    // Default constructor
    // --------------------------------------------------------
    public VMInstance() {
    }


    // --------------------------------------------------------
    // Copy constructor
    // --------------------------------------------------------
    // This is the heart of our prototype implementation.
    //
    // Instead of creating a VM and configuring every field
    // manually, we copy an existing VM.
    // --------------------------------------------------------
    public VMInstance(VMInstance instance) {

        this.os = instance.os;
        this.runtime = instance.runtime;
        this.monitoringAgent = instance.monitoringAgent;
        this.securityPatches = instance.securityPatches;
        this.hostName = instance.hostName;
        this.ipAddress = instance.ipAddress;
    }


    // --------------------------------------------------------
    // Setters
    // --------------------------------------------------------
    public void setOs(String os) {
        this.os = os;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setMonitoringAgent(boolean v) {
        this.monitoringAgent = v;
    }

    public void setSecurityPatches(boolean v) {
        this.securityPatches = v;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    // --------------------------------------------------------
    // Prototype clone()
    // --------------------------------------------------------
    // Creates a completely new VMInstance using the copy
    // constructor.
    //
    // Notice:
    //
    //     return new VMInstance(this);
    //
    // "this" represents the existing prototype.
    // --------------------------------------------------------
    @Override
    public VMInstance clone() {
        return new VMInstance(this);
    }


    // --------------------------------------------------------
    // toString()
    // --------------------------------------------------------
    // Useful for seeing the VM configuration in the output.
    // --------------------------------------------------------
    @Override
    public String toString() {

        return "VMInstance{" +
                "os='" + os + '\'' +
                ", runtime='" + runtime + '\'' +
                ", monitoringAgent=" + monitoringAgent +
                ", securityPatches=" + securityPatches +
                ", hostName='" + hostName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}


// ------------------------------------------------------------
// 3. SPECIALIZED VM TYPE
// ------------------------------------------------------------
// Suppose some VMs have GPUs.
//
// GpuVMInstance inherits all the common VM configuration from
// VMInstance and adds GPU-specific configuration.
// ------------------------------------------------------------
class GpuVMInstance extends VMInstance {

    private String gpuType;


    public GpuVMInstance() {
    }


    // --------------------------------------------------------
    // Copy constructor
    // --------------------------------------------------------
    // First copy everything from VMInstance.
    //
    // Then copy the GPU-specific field.
    // --------------------------------------------------------
    public GpuVMInstance(GpuVMInstance instance) {

        super(instance);

        this.gpuType = instance.gpuType;
    }


    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }


    // --------------------------------------------------------
    // Clone
    // --------------------------------------------------------
    @Override
    public GpuVMInstance clone() {
        return new GpuVMInstance(this);
    }


    @Override
    public String toString() {

        return "GpuVMInstance{" +
                "gpuType='" + gpuType + '\'' +
                ", base=" + super.toString() +
                '}';
    }
}


// ------------------------------------------------------------
// 4. VM IMAGE REGISTRY
// ------------------------------------------------------------
// The registry stores preconfigured VM prototypes.
//
// Think of it like a catalog:
//
// "backend-server-v3" -> preconfigured backend VM
// "gpu-ml-server"    -> preconfigured GPU VM
//
// When a client asks for one, the registry CLONES the prototype
// instead of returning the original object.
// ------------------------------------------------------------
class VMImageRegistry {

    private final Map<String, Prototype<? extends VMInstance>> map =
            new HashMap<>();


    // --------------------------------------------------------
    // Register a prototype
    // --------------------------------------------------------
    public void register(
            String key,
            Prototype<? extends VMInstance> prototype) {

        map.put(key, prototype);
    }


    // --------------------------------------------------------
    // Get a NEW VM from the registry
    // --------------------------------------------------------
    //
    // IMPORTANT:
    //
    // We don't return the object stored in the registry.
    //
    // We return a clone.
    //
    // This protects our original prototype.
    // --------------------------------------------------------
    public VMInstance get(String key) {

        Prototype<? extends VMInstance> prototype = map.get(key);

        if (prototype == null) {
            throw new IllegalArgumentException(
                    "No VM image registered with key: " + key
            );
        }

        return prototype.clone();
    }
}


// ------------------------------------------------------------
// 5. CLIENT
// ------------------------------------------------------------
// The client doesn't need to know how the VM is constructed.
//
// It simply says:
//
//     "Give me a VM based on backend-server-v3"
//
// The registry finds the prototype and clones it.
// ------------------------------------------------------------
class Main {

    public static void main(String[] args) {


        // ====================================================
        // STEP 1: Create the registry
        // ====================================================

        VMImageRegistry registry = new VMImageRegistry();


        // ====================================================
        // STEP 2: Create the "backend-server-v3" prototype
        // ====================================================
        //
        // This could represent a carefully configured production
        // backend server image.
        //
        // We configure it ONCE.
        // ====================================================

        VMInstance backendServerV3 = new VMInstance();

        backendServerV3.setOs("Ubuntu 24.04");
        backendServerV3.setRuntime("Java 21");
        backendServerV3.setMonitoringAgent(true);
        backendServerV3.setSecurityPatches(true);


        // Register the prototype.
        registry.register(
                "backend-server-v3",
                backendServerV3
        );


        // ====================================================
        // STEP 3: Create another prototype
        // ====================================================
        //
        // This time we'll create a GPU machine used for ML
        // workloads.
        // ====================================================

        GpuVMInstance gpuServer = new GpuVMInstance();

        gpuServer.setOs("Ubuntu 24.04");
        gpuServer.setRuntime("Python 3.12");
        gpuServer.setMonitoringAgent(true);
        gpuServer.setSecurityPatches(true);
        gpuServer.setGpuType("NVIDIA A100");


        // Register it under another key.
        registry.register(
                "ml-gpu-server",
                gpuServer
        );


        // ====================================================
        // STEP 4: Request a VM from the registry
        // ====================================================

        VMInstance server1 =
                registry.get("backend-server-v3");


        // ====================================================
        // STEP 5: Give the new VM instance its own identity
        // ====================================================
        //
        // These properties should NOT be part of the shared
        // prototype because every newly created VM needs its
        // own identity.
        // ====================================================

        server1.setHostName("backend-prod-01");
        server1.setIpAddress("10.0.0.101");


        // ====================================================
        // STEP 6: Create another backend VM
        // ====================================================

        VMInstance server2 =
                registry.get("backend-server-v3");

        server2.setHostName("backend-prod-02");
        server2.setIpAddress("10.0.0.102");


        // ====================================================
        // STEP 7: Create a GPU VM
        // ====================================================

        VMInstance mlServer =
                registry.get("ml-gpu-server");

        mlServer.setHostName("ml-server-01");
        mlServer.setIpAddress("10.0.0.200");


        // ====================================================
        // STEP 8: Print the VMs
        // ====================================================

        System.out.println("Backend Server 1:");
        System.out.println(server1);

        System.out.println();

        System.out.println("Backend Server 2:");
        System.out.println(server2);

        System.out.println();

        System.out.println("ML GPU Server:");
        System.out.println(mlServer);

        System.out.println();


        // ====================================================
        // STEP 9: Prove that clones are independent
        // ====================================================

        server1.setRuntime("Java 22");

        System.out.println("After modifying server1:");
        System.out.println();

        System.out.println("server1 runtime = Java 22");
        System.out.println("server2 runtime should still be Java 21");

        System.out.println();
        System.out.println("server1:");
        System.out.println(server1);

        System.out.println();

        System.out.println("server2:");
        System.out.println(server2);
    }
}

//OUTPUT -
//Backend Server 1:
//VMInstance{os='Ubuntu 24.04', runtime='Java 21', monitoringAgent=true, securityPatches=true, hostName='backend-prod-01', ipAddress='10.0.0.101'}
//
//Backend Server 2:
//VMInstance{os='Ubuntu 24.04', runtime='Java 21', monitoringAgent=true, securityPatches=true, hostName='backend-prod-02', ipAddress='10.0.0.102'}
//
//ML GPU Server:
//GpuVMInstance{gpuType='NVIDIA A100', base=VMInstance{os='Ubuntu 24.04', runtime='Python 3.12', monitoringAgent=true, securityPatches=true, hostName='ml-server-01', ipAddress='10.0.0.200'}}
//
//After modifying server1:
//
//server1 runtime = Java 22
//server2 runtime should still be Java 21
//
//server1:
//VMInstance{os='Ubuntu 24.04', runtime='Java 22', monitoringAgent=true, securityPatches=true, hostName='backend-prod-01', ipAddress='10.0.0.101'}
//
//server2:
//VMInstance{os='Ubuntu 24.04', runtime='Java 21', monitoringAgent=true, securityPatches=true, hostName='backend-prod-02', ipAddress='10.0.0.102'}


//---------------------------------------------------------------------------------------------------------------------


// Last class covered Builder, which helps safely construct one complicated object from scratch,
// field by field, with validation, before it is allowed to exist.

//  what if you do not need to build something from scratch at all, because you already have
//  a very similar object sitting around, and all you really need is a second, near-identical one.
//  This is exactly what the Prototype pattern solves, and it is almost always paired with a second,
//  separate pattern called the Registry, which is about storing and retrieving those reusable templates by name.


// The Problem — Copying an Object the Naive Way

//VMInstance original = new VMInstance(...);
//VMInstance copy = new VMInstance();
//
//copy.os = original.os;
//copy.runtime = original.runtime;
//copy.monitoringAgent = original.monitoringAgent;
/// / ...and so on, for every single field

//Problem 1: Tight coupling
//The client has to know every field of VMInstance just to copy it.
//If VMInstance's fields ever change, every place doing this manual
//copying breaks too.

//Problem 2: Encapsulation blocks us
//Private fields with no setter → impossible to copy manually
//from outside the class.

//if (original instanceof GpuVMInstance) {
//    copy = new GpuVMInstance((GpuVMInstance) original);
//} else if (original instanceof VMInstance) {
//    copy = new VMInstance(original);
//}
/// / one more "else if" every time a new type is added — forever

//Problem 3: instanceof chains break OCP
//Every new subtype of VMInstance → one more branch, inside code
//that was already working, that now has to be touched again.


// The Prototype Pattern — The Core Idea
//
// The Cloud VM Analogy
// Think about cloud computing, companies like AWS, Azure, or GCP let you spin up
// virtual machines (VMs). Every VM for a company's "backend server" needs the same OS,
// the same runtime (say, Java 17), the same monitoring agent installed, the same security
// patches, but each VM ends up with its own hostname and IP address once it is actually running.

// "backend-server-v3" VM image (this is literally called an
//AMI — Amazon Machine Image — on AWS):
//  os = "Ubuntu 22.04"        FIXED
//  runtime = "Java 17"         FIXED
//  monitoringAgent = installed  FIXED
//  securityPatches = applied     FIXED
//  hostname = null                  ← varies, set per copy
//  ipAddress = null                    ← varies, set per copy

// The Actual Efficiency Win
// Compared to setting up every VM from a blank machine, the win is: skipping the re-installation
// and re-verification of things that are already known to be correct and shared. Only what is
// actually different this time gets touched, and a new server can go live in seconds instead
// of however long a full install would take.

// Hidden Pitfalls
// Assuming the Prototype pattern is only about "copying an object" in a generic sense. The real
// value is specifically in skipping repeated, already-correct setup work, not just in avoiding a new keyword.

// Confusing "template" with "the only object of its kind." A prototype is meant to be copied many
// times; unlike Singleton (last class), Prototype's whole point is producing many independent
// instances from one trusted source.


//Real Use Cases
//Example 1: A SearchAPI Client

//SearchAPI sapi = new SearchAPI();
//sapi.url = URL;
//sapi.token = TOKEN;
//sapi.timeout = 5000;
/// / ...repeated, on every single call
//sapi.query = "some query";

//SearchAPI prototype = getSearchPrototype();   // fully configured, once
//SearchAPI sapi = prototype.copy();
//sapi.query = "some query";

//Example 2: Notification Templates, and a Second Pattern Hiding Inside

//Email email1 = new Email();
//email1.recipient = "manish@gmail.com";
//email1.greetingName = "Manish";
//email1.subject = "Welcome aboard!";        // same for EVERY welcome email
//email1.senderAddress = "hello@company.com"; // same for EVERY welcome email
//email1.htmlLayout = WELCOME_LAYOUT;          // same for EVERY welcome email
//email1.footer = STANDARD_FOOTER;              // same for EVERY welcome email

//Before we've written any code for this — the idea:
//
//Email welcomeTemplate = new Email();
//welcomeTemplate.subject = "Welcome aboard!";
//welcomeTemplate.senderAddress = "hello@company.com";
//welcomeTemplate.htmlLayout = WELCOME_LAYOUT;
//welcomeTemplate.footer = STANDARD_FOOTER;
//
/// / for every new signup:
//Email email1 = registry.get("welcomeEmail").copy();
//email1.recipient = "manish@gmail.com";
//email1.greetingName = "Manish";

//Hidden Pitfalls
//Building copying logic and template storage into the same class. This mixes two
// separate responsibilities, exactly the SRP violation this section identifies;
// keeping them apart makes each piece easier to understand, test, and change independently.

//Assuming "prototype" and "registry" are two names for the same idea. They solve different
// problems: Prototype is about how an object copies itself; Registry is about where reusable
// templates are kept and looked up by name.


// Coding the Prototype Pattern

// Coding the Prototype Pattern
//What It Means
//Every copyable class needs to guarantee it can be copied, in a predictable way, with a fixed
// method name. The Java construct that forces every class using it to provide a specific method,
// with a specific signature, is an interface.
//
//public interface Prototype<T> {
//    T clone();
//}

//Why Generic?
//Why Prototype<T>, instead of a plain Prototype whose clone() returns Object? So that VMInstance.clone()
// can directly return a VMInstance, without the caller having to cast the result back to the right type.


//Critical rule of Prototype:
//EVERY subclass MUST override clone() itself.
//
//If a child class forgets to override it, calling .clone() on that
//child silently runs the PARENT's clone logic instead — dropping
//any fields the child added. No compiler warning, no crash. Just
//silently wrong data.

// Hidden Pitfalls
// Forgetting to override clone() in a subclass. This is the single most common real-world bug with
// Prototype: the call silently dispatches to the parent's clone(), dropping every field the subclass
// added, with no compiler error and no crash.

// Forgetting to call super(instance) inside a subclass's copy-constructor. Even if the subclass does
// override clone(), skipping super(instance) means the parent's own fields never get copied at all.

// Making the Prototype interface non-generic (Prototype instead of Prototype<T>). This forces every
// caller of clone() to manually cast the result back to the correct type, adding unnecessary friction
// and risk of a wrong cast.


// The Registry — Storing and Retrieving Templates

// Objects now know how to copy themselves, but there is still no place to keep the pre-configured templates,
// for example, a "backend-server-v3" image, so any part of the codebase can retrieve one by name. The simplest
// data structure for "store something, retrieve it later by a key" is a Map.
//
//public class VMImageRegistry {
//    private Map<String, VMInstance> map = new HashMap<>();
//
//    void register(String key, VMInstance instance) {
//        map.put(key, instance);
//    }
//
//    VMInstance get(String key) {
//        return map.get(key);
//    }
//}

// Does this class's one job feel like "copying," or "storing and retrieving templates"? It is purely storage and
// retrieval. Keeping it separate from VMInstance.clone() keeps each class to a single responsibility.

//Filling the Registry
//Some setup or bootstrap code, run once, when the application starts, is responsible for actually populating the registry with templates:
//
//public static void fillRegistry(VMImageRegistry registry) {
//    VMInstance backendImage = new VMInstance();
//    backendImage.setOs("Ubuntu 22.04");
//    backendImage.setRuntime("Java 17");
//    backendImage.setMonitoringAgent(true);
//    backendImage.setSecurityPatches(true);
//    registry.register("backend-server-v3", backendImage);

//    GpuVMInstance gpuImage = new GpuVMInstance();
//    gpuImage.gpuType = "NVIDIA A100";
//    gpuImage.setOs("Ubuntu 22.04");
//    gpuImage.setRuntime("Python 3.11");
//    gpuImage.setMonitoringAgent(true);
//    gpuImage.setSecurityPatches(true);
//    registry.register("gpu-training-v1", gpuImage);

//The Full Flow: Launching One New Backend Server
//
//public static void main(String[] args) {
//    VMImageRegistry vmRegistry = new VMImageRegistry();
//    fillRegistry(vmRegistry);
//
//    VMInstance server = vmRegistry.get("backend-server-v3").clone();
//    server.setHostname("web-01");
//    server.setIpAddress("10.0.0.14");
//}

// In a three-step summary: fillRegistry populates templates once, at startup. .get(key) retrieves the right
// stored template by key. .clone() produces an independent copy that the client can then safely customize.

// Hidden Pitfalls
// Assuming get() on the registry already returns a safe, independent copy. It does not; get() returns the original
// stored template itself. Only .clone(), called afterward, produces an independent object safe to customize.

// Using a plain HashMap for a registry accessed by multiple threads at once. This risks corrupted internal structure or
// silently lost entries; a ConcurrentHashMap is the safer choice under concurrent access.

// Letting get() on a missing key return null silently, with no clear error. The resulting NullPointerException will surface
// later, on a completely different line, far from the actual typo or missing registration that caused it.


// AI Corner — The Shallow-Copy Trap

//What Was Actually Asked, and What AI Gave Back
//"Add a List<String> installedPackages field to this VMInstance
//class and update the clone/copy-constructor accordingly."

//The AI gave us the right SHAPE — a field, a getter, a setter,
//an updated constructor — but silently skipped the one part that
//actually matters for a mutable field: making a genuinely new List.
//
//this.installedPackages = instance.installedPackages;   ❌ shallow — same box
//this.installedPackages = new ArrayList<>(instance.installedPackages);  ✅ deep — new box, same contents

//A Better Prompt, and the Corrected Code
//"Add a List<String> installedPackages field to this VMInstance
//class. Update the clone/copy-constructor so that the copy has
//its own independent List — mutating the copy's list must NOT
//affect the original's list."

//Same lesson as Builder's AI Corner, showing up again here:
//AI is usually good at giving you the right SHAPE of a pattern —
//the field, the getter, the setter, the updated constructor.
//
//It's still your job to check whether that shape actually delivers
//on the promise — here, that a "copy" is truly independent, not
//secretly sharing state with the original. The default prompt got
//the shape right and the substance wrong. A specific prompt,
//naming the exact property you need, got both right.

//Hidden Pitfalls
//Trusting a vague prompt to produce a fully correct copy-constructor for mutable fields.
// A vague prompt often produces code that is structurally correct but shares references for any List
// or Map field, exactly the shape-right, substance-wrong pattern seen with Lombok's @Builder in the previous class.

//Only checking primitive and String fields when reviewing a clone() method. These types cannot be mutated
// after copying, so they are never at risk; the danger is specific to reference types like collections and other objects.

//Assuming this bug will show up immediately during testing. Like the volatile and shared-reference bugs from
// earlier classes, this one is often invisible until two unrelated parts of a system start mysteriously
// affecting each other's data, sometimes long after the code was written.


//Production Use Cases

//Where Prototype and Registry show up in the real world:
//
//- Game dev: enemy/NPC spawning from a pre-configured template,
//  cloned per instance, position/health customized afterward
//- Document platforms: Google Docs / Notion "template gallery" —
//  you're cloning a pre-built template document, not starting blank
//- Kubernetes: Pod/Deployment templates — a spec is defined once,
//  then "copies" (replicas) are stamped out from it
//- Object pooling in performance-sensitive systems: pre-built
//  objects sit in a registry, get cloned/reset and reused, instead
//  of being constructed fresh under load

// Hidden Pitfalls
// Assuming Prototype and Registry only apply to obviously "cloneable" things like VMs or game characters.
// As the database connection config example shows, the same pattern applies anywhere a validated, shared
// configuration needs to be reused with small per-use customizations.


// Java vs Python — Copying Objects

//Python's copy module:
//
//copy.copy(obj)      → a SHALLOW copy
//                       (like the buggy AI-generated code in
//                       Section 6 — new outer object, but any
//                       mutable fields like lists still point to
//                       the SAME original list)
//
//copy.deepcopy(obj)  → a DEEP copy
//                       (like the fixed code in Section 6 — a
//                       brand-new, fully independent copy, all the
//                       way down, including any lists, dicts, or
//                       nested objects inside)

// Hidden Pitfalls
// Assuming copy.copy() in Python is always safe to use. It performs a shallow copy, exactly like the buggy
// AI-generated Java code in Section 6; any mutable field, like a list, will still be shared with the original object.

// Using copy.deepcopy() everywhere by default, without considering cost. A deep copy recursively copies every
// nested object it finds, which can be expensive for large or deeply nested structures; a shallow copy is
// perfectly fine when no mutable fields are involved.


//Common Pitfalls Summary
//1. Writing manual field-by-field copying code outside the class
//   being copied
//     -> causes tight coupling, breaks on private fields with no
//        setter, and forces instanceof chains for subclasses.
//
//2. Using instanceof chains to handle copying different subclasses
//     -> every new subtype means reopening and editing code that
//        already worked, a repeated OCP violation.
//
//3. Forgetting to override clone() in a subclass
//     -> the call silently dispatches to the parent's clone(),
//        dropping every field the subclass added, with no compiler
//        error and no crash.
//
//4. Forgetting super(instance) inside a subclass's copy-constructor
//     -> the parent's own fields never get copied, even if the
//        subclass's own fields are copied correctly.
//
//5. Making the Prototype interface non-generic
//     -> forces every caller to manually cast clone()'s result back
//        to the correct type.
//
//6. Assuming registry.get() already returns a safe, independent copy
//     -> get() returns the original stored template itself; only
//        .clone(), called afterward, produces an independent object.
//
//7. Using a plain HashMap for a registry accessed by multiple
//   threads at once
//     -> risks corrupted internal structure or silently lost
//        entries; use ConcurrentHashMap instead.
//
//8. Letting a missing registry key return null silently
//     -> the resulting NullPointerException surfaces later, on a
//        completely different line, far from the actual typo.
//
//9. Trusting a vague AI prompt to produce a fully correct
//   copy-constructor for mutable fields (List, Map)
//     -> often copies the reference instead of the contents,
//        silently sharing state between "independent" copies.
//
//10. Assuming copy.copy() in Python performs a full, safe copy
//     -> it performs only a shallow copy; mutable fields remain
//        shared with the original object.

//Hidden Pitfalls
//Reaching for Prototype and Registry even when constructing an object fresh is cheap and simple.
// Just as Builder was unnecessary overhead for a tiny Point class, Prototype and Registry add real
// structure and code that only pays off when setup is genuinely expensive or repeated often.


// Key Terminology
//Term	Meaning in Simple Words

//Prototype pattern	A pattern where an object knows how to copy itself, instead of a client rebuilding it from scratch field by field
//Registry pattern	A separate store (commonly a Map) that keeps reusable template objects, retrievable by a name/key
//clone()	The method, defined by the Prototype<T> interface in this class, that an object implements to produce a copy of itself
//Copy-constructor	A constructor that takes an existing object of the same class and copies its field values into a new object
//Shallow copy	A copy where reference-type fields (like a List) still point to the exact same underlying object as the original
//Deep copy	A copy where reference-type fields are also copied into brand-new, independent objects, not just the same reference
//instanceof chain	A ladder of if (x instanceof Y) checks used to handle different subclasses differently; a common OCP red flag
//ConcurrentHashMap	A thread-safe version of HashMap, safe to use when multiple threads read from or write to it at the same time
//copy.copy() (Python)	Python's built-in function for performing a shallow copy of an object
//copy.deepcopy() (Python)	Python's built-in function for performing a deep copy of an object, including all nested mutable fields


// Frequently Asked Interview Questions
//1. What problem does the Prototype pattern solve, and how is it different from Builder?
//Builder solves how to safely construct a new, complex object from scratch, with validation. Prototype solves a
// different problem: when a very similar object already exists, it is often faster and safer to copy that existing
// object and change only what's different, rather than rebuild it from scratch.
//
//2. What are the three problems with manually copying an object's fields from outside the class?
//Tight coupling (the client needs to know every internal field name), encapsulation issues (private fields with no
// setter simply cannot be copied from outside), and OCP violations (handling different subclasses correctly requires
// an ever-growing instanceof chain that must be edited every time a new subtype appears).
//
//3. Why does making the copying logic live inside the class itself fix all three of these problems at once?
//Because code inside the class has full access to all of its own fields, including private ones, it never needs to
// reach in from outside. Each subclass can also implement its own copying logic independently, so adding a new subtype
// touches nothing that already existed.
//
//4. Why is the Prototype<T> interface written as generic, rather than plain Prototype returning Object?
//So that a class like VMInstance can have clone() return a VMInstance directly, without forcing every caller to
// manually cast the result back to the correct type.
//
//5. What happens if a subclass forgets to override clone()?
//The call silently dispatches to the parent class's clone() method instead, which only knows how to copy the parent's
// fields. Any additional fields the subclass added are silently left out of the copy, with no compiler warning and
// no runtime crash, making this a particularly dangerous, hard-to-notice bug.
//
//6. What is the Registry pattern, and why is it kept as a separate class from the objects it stores?
//The Registry is a store, usually a Map, that holds reusable template objects retrievable by name. It is kept separate
// to follow Single Responsibility: copying (Prototype) is one job, and storing/retrieving templates (Registry) is a
// distinct, unrelated job.
//
//7. Why should a registry accessed by multiple threads use ConcurrentHashMap instead of a plain HashMap?
//A plain HashMap is not thread-safe; concurrent reads and writes from multiple threads can corrupt its internal
// structure or silently lose entries. ConcurrentHashMap is designed specifically to handle simultaneous access safely.
//
//8. What happens when Registry.get() is called with a key that was never registered, and why is this dangerous?
//A plain HashMap.get() simply returns null for a missing key, with no error at that point. The actual crash, a
// NullPointerException, only happens later, wherever that null value gets used, for example when .clone() is called
// on it, far away from where the actual mistake (a typo in the key) occurred.
//
//9. What is the difference between a shallow copy and a deep copy, and why does it matter for the Prototype pattern?
//A shallow copy copies an object's fields, but for reference-type fields like a List, it only copies the reference,
// leaving both the original and the copy pointing at the same underlying list. A deep copy creates a brand-new, independent
// version of any such reference-type fields. Prototype's entire promise, that a copy is truly independent of the original,
// is broken if any mutable field is only shallow-copied.
//
//10. Why does AI-generated code for a copy-constructor often get the shape right but the substance wrong, specifically for List or Map fields?
//Because a shallow, reference-only copy still compiles fine, produces a working-looking object, and matches the general
// shape of a copy-constructor. The bug, sharing the same underlying list between two supposedly independent objects, is
// only caught by asking directly whether a field's contents were copied, or just its reference, which a generic prompt often fails to specify.
//

//Section 12: Self-Check Questions
//List the three problems that occur when copying logic is written outside the class being copied, in the naive, manual way.
//Explain, in your own words, why keeping copying logic inside the class fixes the private-field access problem.
//Why is Prototype<T> written as a generic interface instead of a plain interface returning Object?
//Walk through, step by step, what happens when .clone() is called on a GpuVMInstance object that forgot to override clone().
//What must a subclass's copy-constructor call first, before copying its own extra fields, and why?
//Explain the difference in responsibility between the Prototype pattern and the Registry pattern, using the "welcome email" example from Section 3.
//Why is a plain HashMap risky to use inside a Registry accessed by multiple threads, and what should it be replaced with?
//Trace through the shallow-copy example from Section 6: after copy.getInstalledPackages().add("redis") runs, what does original.getInstalledPackages() contain, and why?
//What is the fix for the shallow-copy bug from question 8, and why does it work?
//In Python, what is the key difference between copy.copy() and copy.deepcopy(), and which one corresponds to the "fixed" version of the AI-generated code in Section 6?
