package umbc.ebiquity.kang.ontologypopulating.client.view;

import umbc.ebiquity.kang.ontologypopulating.client.ui.SingleValueReceiver;

import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;

public class OntClassChoosingTextItem extends TextItem implements SingleValueReceiver {
	 private OntClassChosingWindowView dialog;  
	  
     // set the specified value and dismiss the picker dialog  
	 @Override
     public void setSingleValue(String value) {  
         this.setValue(value);  
     }  
     
     public OntClassChoosingTextItem(final ClassifiedTriplesEditWindowView classifiedTriplesEditWindowView) {  
         FormItemIcon formItemIcon = new FormItemIcon();  
         setIcons(formItemIcon);  

         addIconClickHandler(new IconClickHandler() {  
             public void onIconClick(IconClickEvent event) {  

                 // lazily create the YesNoMaybe picker dialog the first time a yesNoMaybe editor is clicked  
                 if (dialog == null) {  
                 	dialog = new OntClassHierarchWindowListView(); 
                 	dialog.singleValueReceiverRegister(OntClassChoosingTextItem.this);
                 	dialog.singleValueReceiverRegister(classifiedTriplesEditWindowView);
                 }  
                 dialog.show();  
             }  
         });  
     }  
 
}
