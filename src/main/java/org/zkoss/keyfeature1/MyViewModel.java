package org.zkoss.keyfeature1;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

@ToClientCommand(MyViewModel.ADD_NEW_MESSAGE)
public class MyViewModel {
	static final String ADD_NEW_MESSAGE = "addNewMessage";
	public static final String HANDLE_PANEL_BUTTON = "handlePanelButton";
	public static final String VIEW_DETAIL = "viewDetail";
	private ListModelList<StatBlock> myStats = new ListModelList<StatBlock>();
	private ListModelList<Message> myMessages = new ListModelList<Message>();
	private String messageTextbox;
	private JSONObject scrollProperty = new JSONObject();




	@Init
	public void init() {
		myStats.add(new StatBlock("totalVisitorClick", "Total Visitors", "3,291,922", "fa-desktop", "green"));
		myStats.add(new StatBlock("bounceRateClick", "Bounce Rate", "20,44%", "fa-chain-broken ", "blue"));
		myStats.add(new StatBlock("uniqueVisitorClick", "Unique Visitors", "1,291,922", "fa-users", "purple"));
		myStats.add(new StatBlock("avgTimeClick", "Avg Time On Site", "00:12:23", "fa-clock-o", "red"));
		
		myMessages.add(new Message(new User("John Doe","./img/user-5.jpg"),"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id nunc non eros fermentum vestibulum ut id felis. Nunc molestie libero eget urna aliquet, vitae laoreet felis ultricies. Fusce sit amet massa malesuada, tincidunt augue vitae, gravida felis."));
		myMessages.add(new Message(new User("Terry Ng","./img/user-6.jpg"),"Sed in ante vel ipsum tristique euismod posuere eget nulla. Quisque ante sem, scelerisque iaculis interdum quis, eleifend id mi. Fusce congue leo nec mauris malesuada, id scelerisque sapien ultricies."));
		myMessages.add(new Message(new User("Fiona Log","./img/user-8.jpg"),"Pellentesque dictum in tortor ac blandit. Nulla rutrum eu leo vulputate ornare. Nulla a semper mi, ac lacinia sapien. Sed volutpat ornare eros, vel semper sem sagittis in. Quisque risus ipsum, iaculis quis cursus eu, tristique sed nulla."));
		myMessages.add(new Message(new User("John Doe","./img/user-7.jpg"),"Morbi molestie lorem quis accumsan elementum. Morbi condimentum nisl iaculis, laoreet risus sed, porta neque. Proin mi leo, dapibus at ligula a, aliquam consectetur metus."));
		
		scrollProperty.put("height", "200px");
		scrollProperty.put("start", "bottom");
		scrollProperty.put("scrollToEnd", ADD_NEW_MESSAGE);
	}

	public ListModelList<Message> getMyMessages() {
		return myMessages;
	}

	public ListModelList<StatBlock> getMyStats() {
		return myStats;
	}
	
	public void setMessageTextbox(String messageTextbox) {
		this.messageTextbox = messageTextbox;
	}
	
	public JSONObject getScrollProperty() {
		return scrollProperty;
	}
	
	@Command(VIEW_DETAIL)
	public void viewDetail(@BindingParam("statId") String statId) {
		Clients.showNotification(statId, null, null, null, 2000);
	}
	
	@Command(HANDLE_PANEL_BUTTON)
	public void handlePanelButton(@BindingParam("buttonId") String buttonId) {
		Clients.showNotification(buttonId, null, null, null, 2000);
	}
	
	@Command
	public void addNewMessage() {
		if (messageTextbox != null && !"".equals(messageTextbox)) {
			myMessages.add(new Message(new User("John Doe", "./img/user-7.jpg"), messageTextbox));
		}
	}

}
