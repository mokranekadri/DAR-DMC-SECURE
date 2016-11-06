package fr.upmc.dar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.json.JSONException;
import org.json.JSONObject;

import fr.upmc.dar.entities.interfaces.IEntity;
import fr.upmc.dar.enums.EventVisibility;

@Entity
public class Event implements IEntity {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	protected User creator;
	
	@ManyToMany(cascade = CascadeType.ALL)
	protected List<User> candidates;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<Comment> comments;
	
	@Column
	private String name;

	@Column
	private EventVisibility privacy;
	
	@Column
	private String description;
	
	@Column
	private String date;
	
	@Column
	private int  commentsCounts;
	
	
	@Column
	private String theme;
	
	@Column
	private String places;
	
	@Column
	private String address;
	
	
	public Event() {
		super();
	}
	
	public Event(	
			User owner,
			String name, 
			String privacy,
			String description,
			String date, 
			String theme, 
			String places,
			String address) throws Exception 
	{
		this.creator = owner;
		this.name = name;
		switch (privacy) {
		case "public":
			this.privacy = EventVisibility.PUBLIC;
			break;
		case "private":
			this.privacy = EventVisibility.PRIVATE;
			break;
		case "group":
			this.privacy = EventVisibility.GROUP;
			break;
		case "university":
			this.privacy = EventVisibility.INTRA_UNI;
			break;
		default:
			throw new Exception("wrong privacy : " + privacy);
		}
		this.description = description;
		this.date = date;
		this.theme = theme;
		this.places = places;
		this.address = address;
		this.candidates = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	
	public Event(	
			User owner,
			String eventName, 
			EventVisibility eventprivacy,
			String eventDescription,
			String eventDate, 
			String eventTheme, 
			String eventPlace,
			String eventAdresse,
			List<Comment> comments) 
	{
		this.creator = owner;
		this.name = eventName;
		this.privacy = eventprivacy;
		this.description = eventDescription;
		this.date = eventDate;
		this.theme = eventTheme;
		this.places = eventPlace;
		this.address = eventAdresse;
		this.comments=comments;
	}
	
	
	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public EventVisibility getEventprivacy() {
		return privacy;
	}



	public void setEventprivacy(EventVisibility eventprivacy) {
		this.privacy = eventprivacy;
	}



	public int getEventCommentsCounts() {
		return commentsCounts;
	}



	public void setEventCommentsCounts(int eventCommentsCounts) {
		this.commentsCounts = eventCommentsCounts;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEventName() {
		return name;
	}
	public void setEventName(String eventName) {
		this.name = eventName;
	}

	public String getEventDescription() {
		return description;
	}
	
	public void setEventDescription(String eventDescription) {
		this.description = eventDescription;
	}
	
	public String getEventDate() {
		return date;
	}
	
	public void setEventDate(String eventDate) {
		this.date = eventDate;
	}
	
	public String getEventTheme() {
		return theme;
	}
	
	public void setEventTheme(String eventTheme) {
		this.theme = eventTheme;
	}
	
	public String getEventPlace() {
		return places;
	}
	
	public void setEventPlace(String eventPlace) {
		this.places = eventPlace;
	}
	
	public String getEventAdresse() {
		return address;
	}
	
	public void setEventAdresse(String eventAdresse) {
		this.address = eventAdresse;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<User> candidates) {
		this.candidates = candidates;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("name", name);
		json.put("privacy", EventVisibility.eventVisibilityToString(privacy));
		json.put("description", description);
		json.put("date", date);
		json.put("places", places);
		json.put("theme", theme);
		json.put("address", address);
		json.put("creator", creator.getUserName());
		json.put("comments", commentsCounts);
		json.put("id", id);
		
		return json;
	}
	
}
