package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageEditor {
	private static Frame origin;
	public static void main(String[] args) throws IOException {
		Frame f = ColorFrame.readFromURL("URL HERE");
		origin = f;
		f.setTitle("TITLE HERE");

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Image Editor");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageEditorModel model = new ImageEditorModel(f);
		ImageEditorView view = new ImageEditorView(main_frame, model);
 		ImageEditorController controller = new ImageEditorController(view, model);
		

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
	public Frame getOriginFrame(){
		return origin;
	}
}
