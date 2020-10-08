package org.zkoss.training.customize;


import java.util.*;
import java.util.Calendar;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

public class ThreeStatesComposer extends SelectorComposer<Component> {
	
	@Wire
	Tree tree1;
	DefaultTreeModel<String> model ;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		initModel();
		tree1.setModel(model);
	}

	private void initModel() {
		DefaultTreeNode root = new DefaultTreeNode(null,
				new DefaultTreeNode[] {
				new DefaultTreeNode("Friend",new DefaultTreeNode[] {
						new DefaultTreeNode("High School", new DefaultTreeNode[] {
								new DefaultTreeNode("Fernando Terrell"),
								new DefaultTreeNode("Stanley Larson")
						}),
						new DefaultTreeNode("University", new DefaultTreeNode[] {
								new DefaultTreeNode("Camryn Breanna"),
								new DefaultTreeNode("Juliana Isabela"),
								new DefaultTreeNode("Holden Craig")
						}),
						new DefaultTreeNode("Emma Jones"),
						//		                    new DefaultTreeNode("Eric Franklin"),
						//		                    new DefaultTreeNode("Alfred Wong"),
						//		                    new DefaultTreeNode("Miguel Soto")
				}),
				new DefaultTreeNode("Work",new DefaultTreeNode[] {
						new DefaultTreeNode("Andrew Willis"),
						new DefaultTreeNode("Russell Thomas"),
						new DefaultTreeNode("Donovan Marcus")
				})
		});
		
		
		model = new TriStateTreeModel<String>(root, true);
		model.setMultiple(true);
	}
	
	@Listen("onClick = #print")
	public void print() {
		Iterator iterator = model.getSelection().iterator();
		while(iterator.hasNext()){
			Clients.log(iterator.next().toString());
		}
	}
}
