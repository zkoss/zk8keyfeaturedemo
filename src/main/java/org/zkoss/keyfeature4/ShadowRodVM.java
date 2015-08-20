package org.zkoss.keyfeature4;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.SmartNotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.zul.ListModelList;

@ToServerCommand({ShadowRodVM.LOAD_DATA_COMMAND})
@ToClientCommand({ShadowRodVM.LOAD_DATA_COMMAND})
public class ShadowRodVM {
	static final String LOAD_DATA_COMMAND = "loadData";
	private int totalSize = 2000;
	private int begin = 0;
	private int cachedSize = 30;
	private int rowHeight = 61;
	private ListModelList<User> usersList;

	@Init
	public void init() {
		usersList = new ListModelList<User>(loadUsers(0));  // simulate load from DB
	}

	@Command(LOAD_DATA_COMMAND)
	@SmartNotifyChange({"topHeight", "bottomHeight"})
	public void loadData(@BindingParam("loadingIndex") int loadingIndex, @BindingParam("direction") String direction) {
		usersList.clear();
		if("down".equals(direction)) {
			usersList.addAll(loadUsers(loadingIndex));
		} else if("up".equals(direction)) {
			usersList.addAll(loadUsers(loadingIndex - cachedSize));
		}
	}

	private List<User> loadUsers(int index) {
		this.begin = Math.max(index, 0);
		return UserService.getUsers(begin, getEnd());
	}

	public ListModelList<User> getUsers() {
		return usersList;
	}

	public int getRowHeight() {
		return rowHeight;
	}

	public String getTopHeight() {
		int topHeight = begin * rowHeight;
		return "height:" + topHeight + "px;";
	}

	public String getBottomHeight() {
		int bottomHeight = (totalSize - getEnd()) * rowHeight;
		return "height:" + bottomHeight + "px;";
	}

	private int getEnd() {
		return Math.min(begin + cachedSize, totalSize - 1);
	}

}
