package br.com.fzlbpms.view.calendar;

import java.time.ZonedDateTime;

import org.vaadin.addon.calendar.item.BasicItem;

public class CalendItem extends BasicItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return super.getCaption();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}

	@Override
	public ZonedDateTime getEnd() {
		// TODO Auto-generated method stub
		return super.getEnd();
	}

	@Override
	public ZonedDateTime getStart() {
		// TODO Auto-generated method stub
		return super.getStart();
	}

	@Override
	public String getStyleName() {
		// TODO Auto-generated method stub
		return super.getStyleName();
	}

	@Override
	public boolean isAllDay() {
		// TODO Auto-generated method stub
		return super.isAllDay();
	}

	@Override
	public void setCaption(String caption) {
		// TODO Auto-generated method stub
		super.setCaption(caption);
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		super.setDescription(description);
	}

	@Override
	public void setEnd(ZonedDateTime end) {
		// TODO Auto-generated method stub
		super.setEnd(end);
	}

	@Override
	public void setStart(ZonedDateTime start) {
		// TODO Auto-generated method stub
		super.setStart(start);
	}

	@Override
	public void setStyleName(String styleName) {
		// TODO Auto-generated method stub
		super.setStyleName(styleName);
	}

	@Override
	public void setAllDay(boolean isAllDay) {
		// TODO Auto-generated method stub
		super.setAllDay(isAllDay);
	}

	@Override
	protected void fireEventChange() {
		// TODO Auto-generated method stub
		super.fireEventChange();
	}

	@Override
	public ItemChangeNotifier getNotifier() {
		// TODO Auto-generated method stub
		return super.getNotifier();
	}

	
}
