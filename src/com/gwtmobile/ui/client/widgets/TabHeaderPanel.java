package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.utils.Utils;



public class TabHeaderPanel extends PanelBase {

	public TabHeaderPanel(){
		setStyleName(Primary.TabHeaderPanel);
	}
	
    @Override
    public void add(Widget w) {
    
    	if (w instanceof TabHeader) {
    		super.add(w);
    	} else if (Beans.isDesignTime() && w instanceof Label) {
    		// allow Label during designtime
    	} else {
    		assert false : "The TabHeaderPanel can only contain multiple TabHeader elements";
    	}
    	
    }
    
    public int getClickedTabHeaderIndex(ClickEvent e) {
        Element div = Element.as(e.getNativeEvent().getEventTarget());
        if (div == this.getElement()) {
        	Utils.Console("Is click on tab header working? " + e.toString());
        	return -1;
        }
        while (div.getParentElement() != this.getElement()) {
            div = div.getParentElement();
        }
        int index = DOM.getChildIndex(
        		(com.google.gwt.user.client.Element)this.getElement(), 
        		(com.google.gwt.user.client.Element)div);
        return index;
    }
    
    public void selectHeader(int index) {
    	if (getWidgetCount() > index) {
	    	TabHeader header = (TabHeader) getWidget(index);
	    	header.addStyleName(Secondary.Selected);
    	}

    }

    public void unSelectHeader(int index) {
    	if (getWidgetCount() > index) {
	    	TabHeader header = (TabHeader) getWidget(index);
	    	header.removeStyleName(Secondary.Selected);
    	}
    }
	
}
