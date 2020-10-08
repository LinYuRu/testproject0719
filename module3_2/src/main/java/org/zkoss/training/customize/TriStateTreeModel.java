package org.zkoss.training.customize;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.TreeNode;

public class TriStateTreeModel<E> extends DefaultTreeModel<E> {
	Map<TreeNode<E>, SelectionState> selectionState = new HashMap<TreeNode<E>, SelectionState>(); //selection state for each node
	
	public TriStateTreeModel(TreeNode<E> node) {
		super(node);
		this.setMultiple(true);
	}

	public TriStateTreeModel(TreeNode<E> root, boolean emptyChildAsLeaf) {
		super(root, emptyChildAsLeaf);
	}

	/**
	 * update selection status of a node's subtree.
	 * Tree already add the selected treenode into the selection, we only add its sub-nodes 
	 * @param node
	 * @param selected
	 */
	public void toggleSubtree(TreeNode<E> node, final boolean selected) {
		
		if (node.getChildren() != null){
			Queue<TreeNode<E>> childQueue = new LinkedList<TreeNode<E>>(node.getChildren());
			while (!childQueue.isEmpty()){
				TreeNode<E> childNode = childQueue.remove();
				if (childNode.getChildren() != null){
					childQueue.addAll(childNode.getChildren());
				}
				if(selected) {
					addToSelection(childNode);
				} else {
					removeFromSelection(childNode);
				}
			}
		}
	}
	
	/**
	 *  update selection status of a node's ancestors / i.e. make them none/partial/full and automatically add/ remove them from selection
	 * @param node
	 * @param selected
	 */
	public void toggleAncestors(TreeNode<E> node, boolean selected) {
		TreeNode<E> ancestorNode = node.getParent();
		while(ancestorNode != getRoot()) {
			if(!selected) {
				removeFromSelection(ancestorNode);
			}
			SelectionState state = calulateSelectionState(ancestorNode);
			selectionState.put(node, state);
			if(state == SelectionState.FULL) {
				addToSelection(ancestorNode);
			}
			ancestorNode = ancestorNode.getParent();
		}
	}

	/**
	 * Performance notice!!! for a larger tree I suggest caching results, as long as the model does not change
	 * @param node
	 * @return
	 */
	public SelectionState calulateSelectionState(TreeNode<E> node) {
		if(isSelected(node)) {
			return SelectionState.FULL; //short circuit
		}
		
		List<TreeNode<E>> children = node.getChildren();
		if(children == null || children.isEmpty()) {
			return SelectionState.NONE;
		}
		
		boolean atLeastOneSelected = false;
		boolean fullySelected = true;
		for (TreeNode<E> child : children) {
			if(isSelected(child)) {
				atLeastOneSelected = true;
			} else {
				SelectionState childSelectionState = calulateSelectionState(child);
				switch (childSelectionState) {
				case FULL:
					atLeastOneSelected = true;
					break;
				case PARTIAL:
					atLeastOneSelected = true;
					fullySelected = false;
					break;
				default:
					fullySelected = false;
					break;
				}
			}
			if(atLeastOneSelected && !fullySelected) return SelectionState.PARTIAL; //short circuit
		}
		if(fullySelected) {
			return SelectionState.FULL;
		} else {
			return SelectionState.NONE;
		} 
	}
	
	public SelectionState getSelectionState(TreeNode<E> node){
		SelectionState state = selectionState.get(node);
		if (state == null){
			return SelectionState.NONE;
		}else{
			return state;
		}
	}
}
