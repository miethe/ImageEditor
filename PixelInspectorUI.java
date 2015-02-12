package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton brush_color_button;
	public boolean buttonPressed=false;
	private FrameView color_preview;
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		brush_color_button = new JButton("Set Brush Color: ");

		color_preview = new FrameView(new ColorFrame(100,100));
		
		setLayout(new GridLayout(4,0));
		add(x_label);
		add(y_label);
		add(pixel_info);
		add(brush_color_button);
		brush_color_button.addActionListener(this);
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));		
	}
	public void updatePreview(int x, int y){
		Frame preview_frame = color_preview.getFrame();
		Pixel p;
		Pixel[] pixelArray = new Pixel[100];
		int n=0;
		ImageEditor f = new ImageEditor();
		Frame newFrame = f.getOriginFrame();
		p = newFrame.getPixel(x, y);
		for (int i=0; i<preview_frame.getWidth(); i++) {
			for (int j=0; j<preview_frame.getHeight(); j++) {
				preview_frame.setPixel(i, j, p);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			setButtonState(true);
	}
	public boolean getButtonState(){
		return buttonPressed;
	}
	public void setButtonState(boolean b){
		buttonPressed=b;
	}
}
