package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import databaseOp.Operation;
import definition.*;

import org.jboss.seam.security.Identity;
import org.picketlink.idm.api.User;
import org.richfaces.event.*;
import org.richfaces.model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import login.ShaEncoder;
@ManagedBean
@RequestScoped
@SuppressWarnings("unused")
public class ReaderBean {
	
	
	@Inject
	private Identity identity;
	
	private List<TaskDef> task;
	private List<TaskDef> oldTask;

	private List<UserDef> users;

	private List<UserDef> oldUsers;

	private List<OrganizationDef> organizations;
	private List<OrganizationDef> oldOrganizations;

	private String email;

	private String password;

	private String pass;

	private String passwordValidate;

	private long idUser;

	private String renderPoll = "true";

	private String xmlFile;

	private String idTask;

	private String username;

	private String changeUsername;

	private String changeOrg;

	private String organization;

	private List<String> editableOrganizations;

	private List<String> editableOwner;

	private String role;

	private String user;

	private String renderArea;

	private String renderButton;

	private String unpublishInformation;
	
	private String publishInformation;

	private String name;
	private Operation op;

	private String tab = "Tasks";

	private String renderTab = "true";

	private String findString;

	private String nieco;

	private boolean sortAscending = true;

	private String owner;

	private String state;

	private String findOption;
	
	private String renderName;
	
	private String renderUpload;
	
	private String renderUsername;
	
	private String renderPassword;
	private String renderPasswordValidate;
	private String renderPasswordNot;
	
	private String renderEmail;
	
	private String renderOrganization;
	
	private String renderRole;
	
	private String renderEmailFormat;
	
	private Pattern pattern;
	private Matcher matcher;
	
	
	private String renderPassword2;
	private String renderPasswordValidate2;
	private String renderPasswordNot2;
	
	private String renderPassword3;
	private String renderPasswordValidate3;
	private String renderPasswordNot3;
	
	private String renderOrg;
	
	private String renderOption;
	
	private String renderFind;
	
private String renderOption2;
	
	private String renderFind2;
	
private String renderOption3;
	
	private String renderFind3;
	private User loggedUser;
	private String loggedUsername;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@PostConstruct
	public void init() {
		try {
			
			
			
			
			setRenderEmailFormat("false");
			setRenderUsername("false");
			setRenderPassword("false");
			setRenderPasswordValidate("false");
			setRenderPasswordNot("false");
			setRenderOrganization("false");
			setRenderEmail("false");
			setRenderRole("false");
			setRenderUpload("false");
			setRenderName("false");
			setRenderArea("false");
			setRenderPoll("true");
			setRenderButton("false");

			setUnpublishInformation("");
			op = new Operation();
			User user = identity.getUser();
			System.out.println("tutu");
			
			String result = op.getUserById(Long.parseLong(user.getId()));
			setLoggedUsername(result);
			System.out.println(result);
			System.out.println(getLoggedUsername());
			organizations = new ArrayList<OrganizationDef>();
			task = new ArrayList<TaskDef>();
			users = new ArrayList<UserDef>();

			editableOrganizations = new ArrayList<String>();
			List<OrganizationDef> resultsOrg = op.getOrganizations();
			for (Object item : resultsOrg) {
				Object[] obj = (Object[]) item;
				organizations.add(new OrganizationDef(obj[0].toString(), obj[1]
						.toString()));
				editableOrganizations.add(obj[1].toString());
			}

			List<TaskDef> resultsTask = op.getAllTasks();
			for (Object item : resultsTask) {
				Object[] obj = (Object[]) item;
				task.add(new TaskDef(obj[0].toString(), obj[1].toString(),
						obj[2].toString(), obj[3].toString(),
						obj[4].toString(), convertIfPublic(obj[5].toString()),
						obj[6].toString(), obj[7].toString(), renderStop(obj[2]
								.toString()), renderRun(obj[2].toString()),
						renderPublish(obj[5].toString(),obj[2].toString()),
						renderUnpublish(obj[5].toString()), renderEdit(obj[2]
								.toString()), renderDelete(obj[2].toString()),renderCommand(obj[2].toString())));

			}

			List<UserDef> resultsUser = op.getAllUsers();
			for (Object item : resultsUser) {
				Object[] obj = (Object[]) item;
				users.add(new UserDef(obj[0].toString(), obj[1].toString(),
						obj[2].toString(), obj[3].toString(),
						obj[4].toString(), obj[5].toString()));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String renderEdit(String state) {
		if (state.equals("MODIFIED") | state.equals("NEW")
				| state.equals("COMPLETE") | state.equals("PAUSED")) {
			return "true";
		}

		else
			return "false";
	}

	private String renderRun(String state) {
		if (state.equals("PAUSED") | state.equals("MODIFIED")
				| state.equals("NEW")) {
			return "true";
		} else {
			return "false";
		}
	}

	private String renderStop(String state) {
		if (state.equals("IN_PROGRESS") | state.equals("WAITING")) {
			return "true";
		} else {
			return "false";
		}
	}

	private String renderPublish(String state,String stat) {
		if ((state.equals("0") && stat.equals("FINISHED")) || state.equals("0") && stat.equals("MODIFIED")) {
			return "true";
		} else {
			return "false";
		}
	}

	private String renderUnpublish(String state) {
		if (state.equals("1")) {
			return "true";
		} else {
			return "false";
		}
	}

	private String renderDelete(String state) {
		if (state.equals("NEW") | state.equals("COMPLETE")
				| state.equals("MODIFIED")) {
			return "true";
		} else {
			return "false";
		}
	}

	public String sortByName() {

		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getName().compareTo(o2.getName());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getName().compareTo(o1.getName());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByUsername() {

		if (sortAscending) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getUsername().compareTo(o2.getUsername());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getUsername().compareTo(o1.getUsername());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByNameOrganization() {

		if (sortAscending) {

			// ascending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o1.getNameOfOrganization().compareTo(
							o2.getNameOfOrganization());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o2.getNameOfOrganization().compareTo(
							o1.getNameOfOrganization());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByIdOrg() {

		if (sortAscending) {

			// ascending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o1.getIdOrganization().compareTo(
							o2.getIdOrganization());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o2.getIdOrganization().compareTo(
							o1.getIdOrganization());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByEmail() {

		if (sortAscending) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getEmail().compareTo(o2.getEmail());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getEmail().compareTo(o1.getEmail());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByRole() {

		if (sortAscending) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getRole().compareTo(o2.getRole());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getRole().compareTo(o1.getRole());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByOrganization() {

		if (sortAscending) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getOrganization().compareTo(o2.getOrganization());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getOrganization().compareTo(o1.getOrganization());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByPermission() {

		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getIfPublic().compareTo(o2.getIfPublic());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getIfPublic().compareTo(o1.getIfPublic());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByOwner() {

		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getOwner().compareTo(o2.getOwner());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getOwner().compareTo(o1.getOwner());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByState() {

		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getState().compareTo(o2.getState());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getState().compareTo(o1.getState());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortByETA() {

		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getEstimatedTime().compareTo(
							o2.getEstimatedTime());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getEstimatedTime().compareTo(
							o1.getEstimatedTime());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public String sortById() {

		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getId().compareTo(o2.getId());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getId().compareTo(o1.getId());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	public void updateEditableOrganizations() {
		this.editableOrganizations = null;
		List<String> org = new ArrayList<String>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;

			org.add(obj[1].toString());
		}
		this.editableOrganizations = org;
	}

	public void updateEditableUsers() {
		this.editableOrganizations = null;
		List<String> org = new ArrayList<String>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;

			org.add(obj[1].toString());
		}
		this.editableOrganizations = org;
	}

	public void updateTasks() {

		List<TaskDef> org = new ArrayList<TaskDef>();
		List<TaskDef> resultsOrg = op.getAllTasks();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;
			org.add(new TaskDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(),
					convertIfPublic(obj[5].toString()), obj[6].toString(),
					obj[7].toString(), renderStop(obj[2].toString()),
					renderRun(obj[2].toString()), renderPublish(obj[5]
							.toString(),obj[2].toString()), renderUnpublish(obj[5].toString()),
					renderEdit(obj[2].toString()), renderDelete(obj[2]
							.toString()),renderCommand(obj[2].toString())));

		}

		this.task = org;
	}

	
	private String renderCommand(String render)
	{
		if (render.equals("0"))
		{
			return "false";
					
		}
		
		else return "true";
		
		
	}
	
	public void setTasks(List<TaskDef> tasks) {
		this.task = tasks;
	}

	/**
	 * Method convert number from database to Private|Public
	 * 
	 * @param ifPublic
	 * @return
	 */
	private String convertIfPublic(String ifPublic) {
		if (ifPublic.equals("0")) {
			return "Private";
		} else {
			return "Public";
		}
	}

	public List<OrganizationDef> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<OrganizationDef> organizations) {
		this.organizations = organizations;
	}

	public List<TaskDef> updateProgress() {
		return this.task;
	}

	public List<UserDef> getUser() {
		return this.users;
	}

	public List<TaskDef> getTask() {
		return this.task;
	}
	
	public void setTask(List<TaskDef> task)
	{
		this.task = task;
	}

	public String saveAction() {

		// get all existing value but set "editable" to false
		for (UserDef order : users) {
			order.setEditable(false);

		}
		updateUsers(this.oldUsers, this.users);
		List<UserDef> user = new ArrayList<UserDef>();
		List<UserDef> resultsUsers = op.getAllUsers();
		for (Object item : resultsUsers) {
			Object[] obj = (Object[]) item;
			user.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString()));

		}
		this.users = user;
		// return to current page
		return null;

	}

	public void updateUser(UserDef user) {
		setIdUser(Long.parseLong(user.getId()));
	}

	private void updateOrganizations(List<OrganizationDef> org,
			List<OrganizationDef> orgChanged) {
		int index = 0;
		for (OrganizationDef element : org) {
			if ((org.get(index).getNameOfOrganization()).equals(orgChanged.get(
					index).getNameOfOrganization()))

			{

			} else {
				if (!(org.get(index).getNameOfOrganization()).equals(orgChanged
						.get(index).getNameOfOrganization())) {

					op.changeOrganization(
							Long.parseLong(org.get(index).getIdOrganization()),
							org.get(index).getNameOfOrganization());
				}

			}

			index++;
		}

	}

	public void saveXmlFile() {

		op.changeXmlFile(getName(), getXmlFile(), getOwner());
		setRenderPoll("true");
		setXmlFile("");
		setName("");
		setOwner("");
		setTab("Tasks");
		setRenderTab("true");
	}

	public void editXmlFile(TaskDef task) {
		setState(task.getState());
		editableOwner = null;
		editableOwner = new ArrayList<String>();
		List<UserDef> resultsUser = op.getAllUsers();
		for (Object item : resultsUser) {
			Object[] obj = (Object[]) item;
			editableOwner.add(obj[1].toString());

		}
		setRenderArea("true");
		setRenderButton("true");
		setXmlFile(task.getXmlFile());
		setIdTask(task.getId());
		setRenderPoll("false");
		setTab("Edit");
		setRenderTab("false");
	}

	public void showAllUsers() {
		List<UserDef> resultsUser = op.getAllUsers();
		List<UserDef> resultsOrg = op.getAllUsers();
		for (Object item : resultsUser) {
			Object[] obj = (Object[]) item;
			resultsOrg.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString()));

		}
		
		this.users = resultsOrg;
		
	}
	
	public void showAllOrganizations()
	{
		List<OrganizationDef> list = new ArrayList<OrganizationDef>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;
			list.add(new OrganizationDef(obj[0].toString(), obj[1]
					.toString()));
			
		}
		this.organizations = list;
	}
	
	
	public void findListOrg() {
		setRenderPoll("false");
		setRenderOption3("false");
		setRenderFind3("false");
		
		
		if (getNieco() == null | getNieco().isEmpty())
		{
			
			
			setRenderFind3("true");
			return;
		}
		
		if (getFindOption() == null | getFindOption().isEmpty())
		{
			
			setRenderOption3("true");
			return;
		}
		

		int index = 0;

		List<OrganizationDef> find = new ArrayList<OrganizationDef>();

		if (getFindOption().equals("Name of Organization")) {

			for (OrganizationDef element : organizations) {
				if (((organizations.get(index).getNameOfOrganization()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new OrganizationDef(organizations.get(index).getIdOrganization(), organizations.get(
							index).getNameOfOrganization()));
							

				}
				index++;
			}

		} else if (getFindOption().equals("ID")) {

			for (OrganizationDef element : organizations) {
				if (((organizations.get(index).getIdOrganization()))
						.equals(getNieco())) {

					find.add(new OrganizationDef(organizations.get(index).getIdOrganization(), organizations.get(
							index).getNameOfOrganization()));
							

				}
				index++;
			}

		}

		setNieco("");
		setFindOption("");
		this.organizations = find;
	}

	public void findListOrganization() {
		setRenderPoll("false");
		setRenderOption("false");
		setRenderFind("false");
		
		
		if (getNieco() == null | getNieco().isEmpty())
		{
			
			
			setRenderFind2("true");
			return;
		}
		
		if (getFindOption() == null | getFindOption().isEmpty())
		{
			
			setRenderOption2("true");
			return;
		}
		

		int index = 0;

		List<UserDef> find = new ArrayList<UserDef>();

		if (getFindOption().equals("Username")) {

			for (UserDef element : users) {
				if (((users.get(index).getUsername()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail()));

				}
				index++;
			}

		} else if (getFindOption().equals("Organization")) {

			for (UserDef element : users) {
				if (((users.get(index).getOrganization()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail()));

				}
				index++;
			}

		} else if (getFindOption().equals("Organization")) {

			for (UserDef element : users) {
				if (((users.get(index).getEmail()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail()));

				}
				index++;
			}

		} else if (getFindOption().equals("Organization")) {

			for (UserDef element : users) {
				if (((users.get(index).getRole()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail()));

				}
				index++;
			}

		}

		setNieco("");
		setFindOption("");
		this.users = find;
	}

	public void findList() {

		setRenderPoll("false");
		setRenderOption("false");
		setRenderFind("false");
		
		
		if (getNieco() == null | getNieco().isEmpty())
		{
			
			
			setRenderFind("true");
			return;
		}
		
		if (getFindOption() == null | getFindOption().isEmpty())
		{
			
			setRenderOption("true");
			return;
		}
		
		
		
		
		int index = 0;

		List<TaskDef> find = new ArrayList<TaskDef>();

		if (getFindOption().equals("Owner")) {

			for (TaskDef element : task) {
				if (((task.get(index).getOwner()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(),task.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),renderCommand(task.get(index).getState())));

				}
				index++;
			}

		} else if (getFindOption().equals("ID")) {
			for (TaskDef element : task) {
				if (((task.get(index).getId())).equals(getNieco())) {

					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(),task.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),renderCommand(task.get(index).getState())));

				}

				index++;
			}

		} else if (getFindOption().equals("Permission")) {
			for (TaskDef element : task) {
				if (((task.get(index).getIfPublic()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(),task.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),renderCommand(task.get(index).getState())));

				}
				index++;
			}

		} else if (getFindOption().equals("Organization")) {
			for (TaskDef element : task) {
				if (((task.get(index).getIfPublic()).toUpperCase())
						.equals(getNieco().toUpperCase())) {
					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(),task.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),renderCommand(task.get(index).getState())));

				}
				index++;
			}

		}
		setRenderPoll("false");
		setRenderOption("false");
		setRenderFind("false");
		setNieco("");
		setFindOption("");
		this.task = find;

	}

	/**
	 * Method update information about users in database
	 * 
	 * @param user
	 * @param userChanged
	 */
	private void updateUsers(List<UserDef> user, List<UserDef> userChanged) {
		int index = 0;
		for (UserDef element : user) {
			if ((user.get(index).getUsername()).equals(userChanged.get(index)
					.getUsername())
					& ((user.get(index).getEmail()).equals(userChanged.get(
							index).getEmail()))
					& ((user.get(index).getRole()).equals(userChanged
							.get(index).getRole()))
					& ((user.get(index).getOrganization()).equals(userChanged
							.get(index).getOrganization()))) {

			} else {
				if (!(user.get(index).getUsername()).equals(userChanged.get(
						index).getUsername())) {

					op.changeUsername(Long.parseLong(user.get(index).getId()),
							userChanged.get(index).getUsername());
				}

				if (!(user.get(index).getEmail()).equals(userChanged.get(index)
						.getEmail())) {

					op.changeEmail(Long.parseLong(user.get(index).getId()),
							element.getEmail());
				}
				if (!(user.get(index).getOrganization()).equals(userChanged
						.get(index).getOrganization())) {

					op.changeOrganizationForUser(
							Long.parseLong(user.get(index).getId()),
							userChanged.get(index).getOrganization());

				}
				if (!(user.get(index).getRole()).equals(userChanged.get(index)
						.getRole())) {
					op.changeUserRole(Long.parseLong(user.get(index).getId()),
							userChanged.get(index).getRole());
				}

			}

			index++;
		}
	}

	public String editActionTask(TaskDef task) {
		setRenderPoll("false");
		System.out.println("som tu");
		this.oldTask = null;
		this.oldTask = new ArrayList<TaskDef>();
		int index = 0;
		task.setEditable(true);
		for (TaskDef u : this.task) {

			this.oldTask.add(new TaskDef(this.task.get(index).getId(),
					this.task.get(index).getName(), this.task.get(index)
							.getState(), this.task.get(index).getProgress(),
					this.task.get(index).getEstimatedTime(), this.task.get(
							index).getIfPublic(), this.task.get(index)
							.getOwner(), this.task.get(index).getXmlFile(),
					renderStop(this.task.get(index).getState()),
					renderRun(this.task.get(index).getState()),
					renderPublish(this.task.get(index).getIfPublic(),this.task.get(index).getState()),
					renderUnpublish(this.task.get(2).getIfPublic()),
					renderEdit(this.task.get(index).getState()),
					renderDelete(this.task.get(index).getState()),renderCommand(this.task.get(index).getState())));
			index++;

		}

		return null;
	}

	public String saveActionTask() {
		updateTasks();

		setRenderPoll("true");

		return null;
	}

	private void updateTaskName(List<TaskDef> task, List<TaskDef> changedTask) {
		int index = 0;
		for (TaskDef element : task) {
			if ((task.get(index).getName()).equals(changedTask.get(index)
					.getName()))

			{

			} else {
				if (!(task.get(index).getName()).equals(changedTask.get(index)
						.getName())) {

					op.changeNameOfTask(
							Long.parseLong(task.get(index).getId()),
							task.get(index).getName());
				}

			}

			index++;
		}

	}

	public String editAction(UserDef user) {

		this.oldUsers = null;
		this.oldUsers = new ArrayList<UserDef>();
		int index = 0;

		user.setEditable(true);
		for (UserDef u : users) {
			this.oldUsers.add(new UserDef(users.get(index).getId(), users.get(
					index).getUsername(), users.get(index).getPassword(), users
					.get(index).getRole(), users.get(index).getOrganization(),
					users.get(index).getEmail()));

			index++;

		}

		return null;
	}

	public void deleteUser(UserDef user) {
		op.deleteUser(user.getUsername(), Long.parseLong(user.getId()));
		List<UserDef> userList = new ArrayList<UserDef>();
		List<UserDef> resultsUsers = op.getAllUsers();
		for (Object item : resultsUsers) {
			Object[] obj = (Object[]) item;
			userList.add(new UserDef(obj[0].toString(), obj[1].toString(),
					obj[2].toString(), obj[3].toString(), obj[4].toString(),
					obj[5].toString()));

		}
		this.users = userList;
	}

	public String saveActionOrganization() {

		// get all existing value but set "editable" to false
		for (OrganizationDef order : organizations) {
			order.setEditable(false);
		}
		updateOrganizations(this.organizations, this.oldOrganizations);

		// return to current page
		return null;

	}

	public String editActionOrganization(OrganizationDef org) {
		int index = 0;
		org.setEditable(true);
		this.oldOrganizations = null;
		this.oldOrganizations = new ArrayList<OrganizationDef>();
		for (OrganizationDef u : this.organizations) {
			this.oldOrganizations.add(new OrganizationDef(organizations.get(
					index).getIdOrganization(), organizations.get(index)
					.getNameOfOrganization()));

			index++;

		}

		return null;
	}

	public void changePasswordForUser() {
		// op.(this.idUser,this.password);

		// op.changePassword(get,this.password);
	}

	public void publishTask(TaskDef task) {
		System.out.println("som tu");
		setRenderPoll("false");
		
		createPublishPage(task.getName(), task.getXmlFile(), task.getId());
		
		
		
		task.setIfPublic("1");
		op.changePermission(Long.parseLong(task.getId()), "1");
		setRenderPoll("true");
	}

	private void createPublishPage(String name, String xml, String id) {
		try {
		File dir = new File("task");
		dir.mkdir();
		}
		catch (Exception ex)
		{
			
		}
		finally {
		File where = new File("Administrator.xhtml");
		String path = where.getAbsolutePath();
		File file = new File(id + ".html");
		path = path.replace("Administrator","task/"+name+".html");
		File page = new File(path);

		try {
			FileWriter fw = new FileWriter(page);

			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<!DOCTYPE html >");
			bw.write("\n");
			bw.write("<html>");
			bw.write("\n");
			bw.write("<head>");
			bw.write("<style>* {background:#eee ! important;} html, body {    width: 100%;}table {    margin: 0 auto;}</style>");
			bw.write("\n");
			bw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\">");
			bw.write("\n");
			bw.write("<script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery-2.1.0.js\"></script>");
			bw.write("\n");
			bw.write("<script language=\"javascript\" type=\"text/javascript\" src=\"js/bootstrap.js\"></script>");
			bw.write("\n");
			bw.write("\n");
			bw.write("</head>");
			bw.write("\n");
			bw.write("<body>");
			
			
			bw.write("<div class=\"container\" style=\"margin:0 px auto\">\n");
			bw.write("<h1>");
			bw.write(name);
			bw.write("</h1>");
			bw.write("<div class=\"jumbotron\">\n");
			bw.write("<plaintext>");
			bw.write(xml);
			bw.write("</plaintext>");
			bw.write("</div>\n");
			bw.write("</body>");
			bw.write("\n");
			bw.write("</html>");
			bw.write("\n");
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		}
	}

	public void runTask(TaskDef task) {
		System.out.println("we call web service method");
		System.out.println(task.getId());
	}

	public void stopTask(TaskDef task) {
		System.out.println("we call web service method");
		System.out.println(task.getId());
	}

	public void createUser() {
		setRenderEmailFormat("false");
		setRenderUsername("false");
		setRenderPassword("false");
		setRenderPasswordValidate("false");
		setRenderPasswordNot("false");
		setRenderOrganization("false");
		setRenderEmail("false");
		setRenderRole("false");
		if (getUsername() == null || getUsername().isEmpty())
		{
			setRenderUsername("true");
			return;
		}
		
		if (getPass() == null || getPass().isEmpty())
		{
			setRenderPassword("true");
			return;
		}
		if (getPasswordValidate() == null || getPasswordValidate().isEmpty())
		{
			setRenderPasswordValidate("true");
			return;
		}
		if (!getPass().equals(getPasswordValidate()))
		{
			setRenderPasswordNot("true");
			return;
		}
		if (getEmail() == null || getEmail().isEmpty())
		{
			setRenderEmail("true");
			return;
		}
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(getEmail());
		if (!matcher.matches())
		{
			setRenderEmailFormat("true");
		return;	
		}
		
		if (getOrganization() == null || getOrganization().isEmpty())
		{
			setRenderOrganization("true");
			return;
		}
		if (getRole() == null || getRole().isEmpty())
		{
			setRenderRole("true");
			return;
		}
		
		
		
		long org = op.getIdOrganization(getOrganization());
		
		
		op.createUser(getUsername(), getPass(), getRole(), getEmail(), org);
		setUsername("");
		setPass("");
		setEmail("");
		setRenderEmailFormat("false");
		setRenderUsername("false");
		setRenderPassword("false");
		setRenderPasswordValidate("false");
		setRenderPasswordNot("false");
		setRenderOrganization("false");
		setRenderEmail("false");
		setRenderRole("false");
		List<UserDef> user = new ArrayList<UserDef>();
		List<UserDef> resultsUsers = op.getAllUsers();
		for (Object item : resultsUsers) {
			Object[] obj = (Object[]) item;
			user.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString()));

		}
		this.users = user;

	}

	public void editUser() {
		long id = op.getIdUser(this.username);
	}

	public void createTask() {
		setRenderName("false");
		setRenderUpload("false");
		
		if (getName() == null || getName().isEmpty())
		{	
			setRenderName("true");
			return;
		}
		
		if (getXmlFile() == null || getXmlFile().isEmpty())
		{
			setRenderUpload("true");
			return;
		}
		
		
		op.createTask(getName(), getXmlFile(), "martin");
	}

	public void unpublishTask(TaskDef task) {

		setRenderPoll("false");
		File f = new File(task.getId() + ".html");

		File file = new File((f.getAbsoluteFile().toString()).replace("bin",
				"standalone/deployments/optaplanner.controller.war"));
		if (file.exists()) {
			file.delete();
		}
		setUnpublishInformation("Task has been unpublished.");

		task.setIfPublic("0");
		op.changePermission(Long.parseLong(task.getId()), "0");
		setRenderPoll("true");
	}

	public void deleteTask(TaskDef task) {

		op.deleteTask(Long.parseLong(task.getId()));

	}

	public void changePassword() {
		
		setRenderPassword3("false");
		setRenderPasswordValidate3("false");
		setRenderPasswordNot("false");
		
		if (getPass() == null | getPass().isEmpty())
		{
			setRenderPassword3("true");
			return;
			
		}
		
		if (getPasswordValidate() == null | getPasswordValidate().isEmpty())
		{
			setRenderPasswordValidate3("true");
			return;
		}
		
		if (!getPass().equals(getPasswordValidate()))
		{
			setRenderPasswordNot3("true");
			return;
		}
		
		
		op.changePassword(getIdUser(), ShaEncoder.hash(getPass()));
		setPass("");
		setPasswordValidate("");
		List<UserDef> user = new ArrayList<UserDef>();
		List<UserDef> resultsUsers = op.getAllUsers();
		for (Object item : resultsUsers) {
			Object[] obj = (Object[]) item;

			user.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString()));

		}
		setRenderPassword3("false");
		setRenderPasswordValidate3("false");
		setRenderPasswordNot("false");
		this.users = user;
	}

	public void listener(FileUploadEvent event) throws Exception {
		UploadedFile item = event.getUploadedFile();

		setXmlFile(new String(item.getData()));

	}

	public void createOrganization() {
		
		
		setRenderOrg("false");
		if (getOrganization() == null | getOrganization().isEmpty())
		{
			setRenderOrg("true");
			return;
			
			
		}
		setRenderOrg("false");
		op.createOrganization(this.organization);
		updateOrganization();

	}

	public void updateOrganization() {

		List<OrganizationDef> org = new ArrayList<OrganizationDef>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;
			org.add(new OrganizationDef(obj[0].toString(), obj[1].toString()));

		}
		this.organizations = org;
	}

	public void deleteOrganization(OrganizationDef org) {

		op.deleteOrganization(Long.parseLong(org.getIdOrganization()));
		updateOrganization();
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			context.redirect("Login.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordValidate() {
		return passwordValidate;
	}

	public void setPasswordValidate(String passwordValidate) {
		this.passwordValidate = passwordValidate;
	}

	public void validatePassword() {
		setRenderPassword2("false");
		setRenderPasswordValidate2("false");
		setRenderPasswordNot2("false");
		if (getPassword() == null || getPassword().isEmpty())
		{
			setRenderPassword2("true");
			return;
		}
		
		if (getPasswordValidate() == null || getPasswordValidate().isEmpty())
		{
			
			setRenderPasswordValidate2("true");
			return;
		}
		
		if (!getPassword().equals(getPasswordValidate()))
		{
			
			setRenderPasswordNot2("true");
			return;
		}
		
		op.changePasswordForUser("martin", this.password);
		setRenderPassword2("false");
		setRenderPasswordValidate2("false");
		setRenderPasswordNot2("false");
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setEditableOrganizations(List<String> org) {
		this.editableOrganizations = org;
	}

	public List<String> getEditableOrganizations() {
		return editableOrganizations;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String password) {
		this.pass = password;
	}

	public void setIdUser(long id) {
		this.idUser = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setRenderPoll(String render) {
		this.renderPoll = render;
	}

	public String getRenderPoll() {
		return renderPoll;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	private void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	private String getIdTask() {
		return this.idTask;
	}

	public String getRenderArea() {
		return renderArea;
	}

	public void setRenderArea(String render) {
		this.renderArea = render;
	}

	public void setRenderButton(String render) {
		this.renderButton = render;
	}

	public String getRenderButton() {
		return renderButton;
	}

	public String getUnpublishInformation() {
		return unpublishInformation;
	}

	public void setUnpublishInformation(String information) {
		this.unpublishInformation = information;
	}

	public void setPublishInformation(String information) {
		this.publishInformation = information;
	}

	public String getPublishInformation() {
		return publishInformation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getTab() {
		return tab;
	}

	public void setRenderTab(String renderTab) {
		this.renderTab = renderTab;
	}

	public String getRenderTab() {
		return renderTab;
	}

	public String getFindString() {
		return findString;
	}

	public void setFindString(String findString) {
		this.findString = findString;
	}

	public List<String> getEditableOwner() {
		return editableOwner;
	}

	public void setEditableOwner(List<String> list) {
		this.editableOwner = list;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFindOption() {
		return findOption;
	}

	public void setFindOption(String option) {
		this.findOption = option;
	}

	public void setNieco(String nieco) {
		this.nieco = nieco;

	}

	public String getNieco() {
		return nieco;
	}
	
	public String getRenderName()
	{
		return renderName;
	}
	
	public void setRenderName(String name)
	{
		this.renderName = name;
	}
	
	public void setRenderUpload(String render)
	{
		this.renderUpload = render;
	}
	
	public String getRenderUpload()
	{
		return renderUpload;
	}
	
	public void setRenderUsername(String name)
	{
		this.renderUsername = name;
	}
	
	public String getRenderUsername()
	{
		return renderUsername;
	}
	
	public void setRenderPassword(String name)
	{
		this.renderPassword = name;
	}
	
	public String getRenderPassword()
	{
		return renderPassword;
	}
	
	public void setRenderPasswordValidate(String name)
	{
		this.renderPasswordValidate = name;
	}
	
	public String getRenderPasswordValidate()
	{
		return renderPasswordValidate;
	}
	
	public void setRenderPasswordNot(String name)
	{
		this.renderPasswordNot = name;
	}
	
	public String getRenderPasswordNot()
	{
		return renderPasswordNot;
	}

	public void setRenderEmail(String name)
	{
		this.renderEmail = name;
	}
	
	public String getRenderEmail()
	{
		return renderEmail;
	}

	public void setRenderOrganization(String name)
	{
		this.renderOrganization = name;
	}
	
	public String getRenderOrganization()
	{
		return renderOrganization;
	}


	public void setRenderRole(String name)
	{
		this.renderRole = name;
	}
	
	public String getRenderRole()
	{
		return renderRole;
	}

	public void setRenderEmailFormat(String format)
	{
		this.renderEmailFormat = format;
	}
	
	public String getRenderEmailFormat()
	{
		return renderEmailFormat;
	}
	

	public void setRenderPassword2(String name)
	{
		this.renderPassword2 = name;
	}
	
	public String getRenderPassword2()
	{
		return renderPassword2;
	}
	
	public void setRenderPasswordValidate2(String name)
	{
		this.renderPasswordValidate2 = name;
	}
	
	public String getRenderPasswordValidate2()
	{
		return renderPasswordValidate2;
	}
	
	public void setRenderPasswordNot2(String name)
	{
		this.renderPasswordNot2 = name;
	}
	
	public String getRenderPasswordNot2()
	{
		return renderPasswordNot2;
	}
	
	
	public void setRenderPassword3(String name)
	{
		this.renderPassword3 = name;
	}
	
	public String getRenderPassword3()
	{
		return renderPassword3;
	}
	
	public void setRenderPasswordValidate3(String name)
	{
		this.renderPasswordValidate3 = name;
	}
	
	public String getRenderPasswordValidate3()
	{
		return renderPasswordValidate3;
	}
	
	public void setRenderPasswordNot3(String name)
	{
		this.renderPasswordNot3 = name;
	}
	
	public String getRenderPasswordNot3()
	{
		return renderPasswordNot3;
	}
	
	
	public void setRenderOrg(String org)
	{
		this.renderOrg = org;
	}
	
	public String getRenderOrg()
	{
		return renderOrg;
	}
	
	
	public void setRenderFind(String render)
	{
		this.renderFind = render;
	}
	
	public String getRenderFind()
	{
		return renderFind;
	}
	
	public void setRenderOption(String option)
	{
		this.renderOption = option;
	}
	
	public String getRenderOption()
	{
		return renderOption;
	}
	
	
	
	public void setRenderFind2(String render)
	{
		this.renderFind2 = render;
	}
	
	public String getRenderFind2()
	{
		return renderFind2;
	}
	
	public void setRenderOption2(String option)
	{
		this.renderOption2 = option;
	}
	
	public String getRenderOption2()
	{
		return renderOption2;
	}
	
	
	public void setRenderFind3(String render)
	{
		this.renderFind3 = render;
	}
	
	public String getRenderFind3()
	{
		return renderFind3;
	}
	
	public void setRenderOption3(String option)
	{
		this.renderOption3 = option;
	}
	
	public String getRenderOption3()
	{
		return renderOption3;
	}
	
	public void setUsers(List<UserDef> users)
	{
		this.users = users;
	}
	
	public List<UserDef> getUsers()
	{
		return users;
	}
	
	public void setChangeUsername(String username)
	{
		this.changeUsername = username;
	}
	
	public String getChangeUsername()
	{
		return changeUsername;
	}
	
	public void setChangeOrg(String org)
	{
		this.changeOrg = org;
	}
	
	public String getChangeOrg()
	{
		return changeOrg;
	}
	
	public void setLoggedUsername(String username)
	{
		this.loggedUsername = username;
	}
	
	
	public String getLoggedUsername()
	{
		return loggedUsername;
	}

}
