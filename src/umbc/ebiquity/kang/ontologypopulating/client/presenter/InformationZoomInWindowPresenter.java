package umbc.ebiquity.kang.ontologypopulating.client.presenter;

import umbc.ebiquity.kang.ontologypopulating.client.view.InformationZoomInWindowView;

import com.google.gwt.user.client.ui.Widget;

public class InformationZoomInWindowPresenter {
	
	public interface Display {
		public Widget asWidget();

		void showWindow();
	}

	private Display display;
	public InformationZoomInWindowPresenter(String info) {
		display = new InformationZoomInWindowView(info);
	}

	public void showInformationZoomInWindow(){
		display.showWindow();
	}
}
