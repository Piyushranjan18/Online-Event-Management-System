public class Event {
    public int eventId;
    public String title;
    public String description;
    public String date;
    public int organizerId;
    public String status;

    public Event(int id, String t, String d, String dt, int org, String st) {
        this.eventId = id;
        this.title = t;
        this.description = d;
        this.date = dt;
        this.organizerId = org;
        this.status = st;
    }
}
