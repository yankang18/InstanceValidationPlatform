package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.Collection;
import java.util.LinkedHashMap;

import umbc.ebiquity.kang.ontologypopulating.client.ui.SingleValueReceiver;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleOntClassInfo;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class OntClassHierarchWindowListView extends OntClassChosingWindowView {
	
	private RadioGroupItem recommededClassesGroupItem;

	public OntClassHierarchWindowListView() {
		super();
	}

	@Override
	protected Canvas createEmptyView() {
		recommededClassesGroupItem = new RadioGroupItem();
		VLayout dialogLayout = new VLayout(3);
		HLayout buttonLayout = new HLayout(3);
		IButton okButton = new IButton("OK");
		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for (SingleValueReceiver singleValueReceiver : singleValueReceivers) {
					singleValueReceiver.setSingleValue(recommededClassesGroupItem.getValueAsString());
				}
				OntClassHierarchWindowListView.this.hide();
			}
		});

		IButton yesButton = new IButton("Cancel");
		yesButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				OntClassHierarchWindowListView.this.hide();
			}
		});

		buttonLayout.addMember(okButton);
		buttonLayout.addMember(yesButton);
		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setTitle("");
		dynamicForm.setOverflow(Overflow.SCROLL);
		dynamicForm.setShowEdges(true);
		dynamicForm.setGroupTitle("Instance Update");
		dynamicForm.setMargin(2);
		// dynamicForm.setNumCols(2);
		// dynamicForm.setColWidths("*", 0);
		dynamicForm.setAutoFocus(false);

		dynamicForm.setFields(recommededClassesGroupItem);
		dynamicForm.setWidth100();
		dynamicForm.setHeight100();
		dialogLayout.addMember(dynamicForm);
		dialogLayout.addMember(buttonLayout);
		return dialogLayout;
	}

	@Override
	protected void populateView(Collection<SimpleOntClassInfo> data) {
		LinkedHashMap<String, String> recommendedClasses = new LinkedHashMap<String, String>();
		for (SimpleOntClassInfo ontClassInfo : data) {
			recommendedClasses.put(ontClassInfo.getOntClassLabel(), ontClassInfo.getOntClassLabel());
		}
		recommededClassesGroupItem.setValueMap(recommendedClasses);
	}

}
