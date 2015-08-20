package org.zkoss.keyfeature4;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.SmartNotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.zul.ListModelList;

@ToServerCommand({"scrolling"})
@ToClientCommand({"scrolling"})
public class ShadowRodVM {
	private int totalSize = 2000;
	private int cachedSize = 30;
	private int rowHeight = 62;
	private int topHeight = 0;
	private int bottomHeight = 0;
	private int visibleSize = 10;
	private int begin = 0;
	private int end = visibleSize * 3;
	private ListModelList<User> usersList;

	@Init
	public void init() {
		usersList = new ListModelList<User>(load(0, cachedSize));  // simulate load from DB
		topHeight = begin * rowHeight;
		bottomHeight = (totalSize - end) * rowHeight;
	}

	@Command("scrolling")
	@SmartNotifyChange({"begin", "end", "topHeight", "bottomHeight"})
	public void doScrolling(@BindingParam("begin") int begin,
			@BindingParam("end") int end) {
		usersList.clear();
		usersList.addAll(load(begin, end));
	}

	private List<User> load(int begin, int end) {
		if(end > totalSize) {
			end = totalSize;
			begin = end - cachedSize;
		}
		this.begin = begin;
		this.end = end;
		return UserService.getUsers(begin, end);
	}

	public ListModelList<User> getUsers() {
		return usersList;
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

	public String getTopHeight() {
		topHeight = begin * rowHeight;
		return "height:" + topHeight + "px;";
	}

	public String getBottomHeight() {
		bottomHeight = (totalSize - end) * rowHeight;
		return "height:" + bottomHeight + "px;";
	}

	public int getBegin() {
		return begin;
	}

	public int getVisibleSize() {
		return visibleSize;
	}

	public int getEnd() {
		return end;
	}

}
