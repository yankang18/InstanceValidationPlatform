package umbc.ebiquity.kang.ontologypopulating.client;

import java.util.ArrayList;
import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.client.presenter.ApplicationPresenter;
import umbc.ebiquity.kang.ontologypopulating.client.view.ApplicationView;
import umbc.ebiquity.kang.ontologypopulating.client.view.MatchedSubject2OntoClassPairView;
import umbc.ebiquity.kang.ontologypopulating.shared.FieldVerifier;
import umbc.ebiquity.kang.ontologypopulating.shared.MatchedTerm2OntClassInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OntologyPopulating implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	private final OntologyMatchingServiceAsync ontologyMatchingService = GWT.create(OntologyMatchingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		ApplicationPresenter appPresenter = new ApplicationPresenter(ontologyMatchingService);
		RootPanel.get("body").add(appPresenter.getPresentation());
		
//		ontologyMatchingService
//				.getMatchedTerm2OntClassInfo(new AsyncCallback<Collection<MatchedTerm2OntClassInfo>>() {
//
//					@Override
//					public void onSuccess(Collection<MatchedTerm2OntClassInfo> result) {
//						ApplicationView appView = new ApplicationView();
//						for (MatchedTerm2OntClassInfo matchedPair : result) {
//
//							Collection<String> relatedTerms = new ArrayList<String>();
//							Collection<String> ontClasses = new ArrayList<String>();
//
//							String termLabel = matchedPair.getTermLabel();
//							String classLabel = matchedPair.getOntClassLabel();
//							double sim = matchedPair.getSimilarity();
//							relatedTerms.addAll(matchedPair.getRelatedTerms());
//							ontClasses.addAll(matchedPair.getSuperOntClassesInPath());
//
//							MatchedSubject2OntoClassPairView pairView = new MatchedSubject2OntoClassPairView(
//									relatedTerms, ontClasses, sim);
//							pairView.setMatchedClassLabel(classLabel);
//							pairView.setSubjectLabel(termLabel);
//							appView.addMatchedPair(pairView);
//						}
//						
//						Widget widget = appView.asWidget();
//						RootPanel.get("body").add(widget);
//
//					}
//
//					@Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//
//					}
//				});
		
		
//		Collection<String> relatedTerms = new ArrayList<String>();
//		relatedTerms.add("Cutting");
//		relatedTerms.add("Laser Cutting");
//		Collection<String> ontClasses = new ArrayList<String>();
//		ontClasses.add("WaterJetCutting");
//		ontClasses.add("Manufacturing");
//		ontClasses.add("Service");
//		MatchedSubject2OntoClassPairView pairView = new MatchedSubject2OntoClassPairView(relatedTerms, ontClasses, 0.88);
//		pairView.setMatchedClassLabel("Tile Laser Cutting");
//		pairView.setSubjectLabel("Laser Cutting");
//		MatchedSubject2OntoClassPairView pairView2 = new MatchedSubject2OntoClassPairView(relatedTerms, ontClasses, 0.88);
//		pairView2.setMatchedClassLabel("Laser Cutting");
//		pairView2.setSubjectLabel("Cutting");
//		
//		ApplicationView appView = new ApplicationView();
//		appView.addMatchedPair(pairView);
//		appView.addMatchedPair(pairView2);
//		Widget widget = appView.asWidget();
//		
//		RootPanel.get("body").add(widget);
		
//		final Button sendButton = new Button("Send");
//		final TextBox nameField = new TextBox();
//		nameField.setText("GWT User");
//		final Label errorLabel = new Label();
//
//		// We can add style names to widgets
//		sendButton.addStyleName("sendButton");
//
//		// Add the nameField and sendButton to the RootPanel
//		// Use RootPanel.get() to get the entire body element
//		RootPanel.get("nameFieldContainer").add(nameField);
//		RootPanel.get("sendButtonContainer").add(sendButton);
//		RootPanel.get("errorLabelContainer").add(errorLabel);
//
//		// Focus the cursor on the name field when the app loads
//		nameField.setFocus(true);
//		nameField.selectAll();
//
//		// Create the popup dialog box
//		final DialogBox dialogBox = new DialogBox();
//		dialogBox.setText("Remote Procedure Call");
//		dialogBox.setAnimationEnabled(true);
//		final Button closeButton = new Button("Close");
//		// We can set the id of a widget by accessing its Element
//		closeButton.getElement().setId("closeButton");
//		final Label textToServerLabel = new Label();
//		final HTML serverResponseLabel = new HTML();
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(textToServerLabel);
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.add(serverResponseLabel);
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		dialogVPanel.add(closeButton);
//		dialogBox.setWidget(dialogVPanel);
//
//		// Add a handler to close the DialogBox
//		closeButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				dialogBox.hide();
//				sendButton.setEnabled(true);
//				sendButton.setFocus(true);
//			}
//		});
//
//		// Create a handler for the sendButton and nameField
//		class MyHandler implements ClickHandler, KeyUpHandler {
//			/**
//			 * Fired when the user clicks on the sendButton.
//			 */
//			public void onClick(ClickEvent event) {
//				sendNameToServer();
//			}
//
//			/**
//			 * Fired when the user types in the nameField.
//			 */
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					sendNameToServer();
//				}
//			}
//
//			/**
//			 * Send the name from the nameField to the server and wait for a response.
//			 */
//			private void sendNameToServer() {
//				// First, we validate the input.
//				errorLabel.setText("");
//				String textToServer = nameField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}
//
//				// Then, we send the input to the server.
//				sendButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");
//				greetingService.greetServer(textToServer,
//						new AsyncCallback<String>() {
//							public void onFailure(Throwable caught) {
//								// Show the RPC error message to the user
//								dialogBox
//										.setText("Remote Procedure Call - Failure");
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//
//							public void onSuccess(String result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(result);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//						});
//			}
//		}
//
//		// Add a handler to send the name to the server
//		MyHandler handler = new MyHandler();
//		sendButton.addClickHandler(handler);
//		nameField.addKeyUpHandler(handler);
	}
}
