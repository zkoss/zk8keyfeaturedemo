package org.zkoss.keyfeature4;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyCommand;
import org.zkoss.bind.annotation.SmartNotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.zul.ListModelList;

@ToServerCommand(ShadowRodVM.LOAD_DATA_COMMAND)
@ToClientCommand({ShadowRodVM.LOAD_DATA_COMMAND, ShadowRodVM.UPDATE_BEGIN})
@NotifyCommand(value = ShadowRodVM.UPDATE_BEGIN, onChange="_vm_.begin")
public class ShadowRodVM {
	static final String UPDATE_BEGIN = "updateBegin";
	static final String LOAD_DATA_COMMAND = "loadData";
	private int begin = 0;
	private int cachedSize = 30;
	private int totalSize;
	private int rowHeight = 61;
	private ListModelList<User> usersList;

	@Init
	public void init() {
		totalSize = UserService.getUsersCount();
		usersList = new ListModelList<User>(loadUsers(0));  // simulate load from DB
	}

	@Command(LOAD_DATA_COMMAND)
	@SmartNotifyChange({"begin"})
	public void loadData(@BindingParam("loadingIndex") int loadingIndex, @BindingParam("direction") String direction) {
		usersList.clear();
		if("down".equals(direction)) {
			usersList.addAll(loadUsers(loadingIndex));
		} else if("up".equals(direction)) {
			usersList.addAll(loadUsers(loadingIndex - cachedSize));
		}
	}

	private List<User> loadUsers(int index) {
		this.begin = Math.min(Math.max(index, 0), totalSize - cachedSize);
		int end = Math.min(begin + cachedSize, totalSize);
		return UserService.getUsers(begin, end);
	}

	public ListModelList<User> getUsers() {
		return usersList;
	}

	public int getBegin() {
		return begin;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public int getCachedSize() {
		return cachedSize;
	}

	public int getRowHeight() {
		return rowHeight;
	}

}
