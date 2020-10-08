package org.zkoss.training.customize;

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

public class TriStateTreeitemRenderer implements TreeitemRenderer<DefaultTreeNode> {

	@Override
	public void render(final Treeitem item, DefaultTreeNode node, int index)
			throws Exception {
		Treecell cell = new Treecell(Objects.toString(node));
		Treerow row = null;
		item.setValue(node);
		if(item.getTreerow()==null){
			row = new Treerow();
			row.setParent(item);
		}else{
			row = item.getTreerow();
			row.getChildren().clear();
		}
		cell.setParent(row);
		TriStateTreeModel model = (TriStateTreeModel)node.getModel();
		if (model.getSelectionState(node) == SelectionState.PARTIAL){
			item.setSclass(SelectionState.PARTIAL.name().toLowerCase());
		}else{
			item.setSclass("");
		}
		
		item.addEventListener(TriStateTree.ON_UPDATE_ICON, new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				if (event.getData()== SelectionState.PARTIAL){
					item.getTreerow().setSclass(SelectionState.PARTIAL.name().toLowerCase());
				}else{
					item.getTreerow().setSclass("");
				}
			}
		});
	}

}
