package org.zkoss.training.customize;

import java.util.HashSet;
import java.util.Set;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treeitem;


public class TriStateTree extends Tree {
	public static final String ON_UPDATE_ICON = "onUpdateIcon"; 

	public TriStateTree() {
		super();
		this.setItemRenderer(new TriStateTreeitemRenderer());
		//handle cascading selection for a tree node
		addEventListener(Events.ON_SELECT, new EventListener<SelectEvent>() {

			@Override
			public void onEvent(SelectEvent event) throws Exception {
				Treeitem selectedItem = (Treeitem)event.getReference();
				TriStateTreeModel model = getModel();
				Set<Treeitem> previousSelectedItems = new HashSet<Treeitem>(selectedItem.getTree().getSelectedItems());
				model.toggleSubtree((DefaultTreeNode)selectedItem.getValue(), selectedItem.isSelected());
				model.toggleAncestors((DefaultTreeNode)selectedItem.getValue(), selectedItem.isSelected());
				
				Set<Treeitem> selectedItems = selectedItem.getTree().getSelectedItems();
				//update currentItem;
				Events.sendEvent(ON_UPDATE_ICON, selectedItem, selectedItem.isSelected() ? SelectionState.FULL : SelectionState.NONE);
				//update child items;
				for (Treeitem treeitem : selectedItems) {
					Events.sendEvent(ON_UPDATE_ICON, treeitem, SelectionState.FULL);
				}
				//update unselected icons
				previousSelectedItems.removeAll(selectedItems);
				for (Treeitem treeitem : previousSelectedItems) {
					Events.sendEvent(ON_UPDATE_ICON, treeitem, SelectionState.NONE);
				}
				//update ancestor icons
				Treeitem ancestorItem = selectedItem.getParentItem();
				while (ancestorItem != null) {
					DefaultTreeNode ancestorNode = ancestorItem.getValue();
					if(ancestorNode == null) break; //stop at root
					Events.sendEvent(ON_UPDATE_ICON, ancestorItem, model.calulateSelectionState(ancestorNode));
					ancestorItem = ancestorItem.getParentItem();
				}
			}
		});
	}
		
	@Override
	public TriStateTreeModel getModel() {
		TreeModel model = super.getModel();
		if (model instanceof TriStateTreeModel){
			return (TriStateTreeModel)model;
		}else{
			throw new IllegalStateException("This component can only accept a model with class "+TriStateTreeModel.class);
		}
	}
}
