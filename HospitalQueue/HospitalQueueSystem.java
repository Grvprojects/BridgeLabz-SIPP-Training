import java.util.*;

// Generic class to represent any type of appointment
class Appointment<T> {
    private String patientName;
    private T appointmentType; // Can be doctorName, testType, etc.
    private String timeSlot;

    public Appointment(String patientName, T appointmentType, String timeSlot) {
        this.patientName = patientName;
        this.appointmentType = appointmentType;
        this.timeSlot = timeSlot;
    }

    public String getPatientName() {
        return patientName;
    }

    public T getAppointmentType() {
        return appointmentType;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "Patient: " + patientName + ", Type: " + appointmentType + ", Time Slot: " + timeSlot;
    }
}

// Generic class to manage a queue of appointments
class AppointmentQueue<T> {
    private Queue<Appointment<T>> queue;

    public AppointmentQueue() {
        this.queue = new LinkedList<>();
    }

    // Add appointment
    public void bookAppointment(String patientName, T type, String timeSlot) {
        Appointment<T> appointment = new Appointment<>(patientName, type, timeSlot);
        queue.offer(appointment);
        System.out.println("Appointment booked: " + appointment);
    }

    // Serve the next patient
    public void serveNext() {
        Appointment<T> appointment = queue.poll();
        if (appointment != null) {
            System.out.println("Serving Appointment: " + appointment);
        } else {
            System.out.println("No appointments to serve.");
        }
    }

    // View next appointment
    public void viewNext() {
        Appointment<T> appointment = queue.peek();
        if (appointment != null) {
            System.out.println("Next Appointment: " + appointment);
        } else {
            System.out.println("No upcoming appointments.");
        }
    }

    // Show all appointments
    public void showAll() {
        if (queue.isEmpty()) {
            System.out.println("No appointments.");
        } else {
            System.out.println("All Appointments:");
            for (Appointment<T> appt : queue) {
                System.out.println(appt);
            }
        }
    }
}

// Main class to demonstrate
public class HospitalQueueSystem {
    public static void main(String[] args) {
        // Doctor Appointment Queue
        AppointmentQueue<String> doctorQueue = new AppointmentQueue<>();
        doctorQueue.bookAppointment("Alice", "Dr. Smith", "10:00 AM");
        doctorQueue.bookAppointment("Bob", "Dr. Jones", "10:30 AM");
        doctorQueue.bookAppointment("Charlie", "Dr. Smith", "11:00 AM");

        doctorQueue.viewNext(); // View who is next
        doctorQueue.serveNext(); // Serve first appointment
        doctorQueue.showAll();   // Show remaining

        System.out.println("\n---- LAB TEST Queue ----");

        // Lab Test Queue (Generic Reuse)
        AppointmentQueue<String> labTestQueue = new AppointmentQueue<>();
        labTestQueue.bookAppointment("David", "Blood Test", "11:30 AM");
        labTestQueue.bookAppointment("Eva", "X-Ray", "12:00 PM");

        labTestQueue.viewNext();
        labTestQueue.serveNext();
        labTestQueue.showAll();
    }
}
