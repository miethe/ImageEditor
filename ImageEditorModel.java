package a8;

public class ImageEditorModel {

	private Frame original;
	private Frame current;
	public MyStack paintStack = new MyStack(300);
	private Pixel brushPixel;
	private boolean mouseDown;
	
	public ImageEditorModel(Frame f) {
		original = f;
		current = original.copy();
	}

	public Frame getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendNotifications();
		if(!paintStack.isFull()){
			paintStack.push(current.copy());
		}
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeNotifications();
	}
	public void setBrushPixel(int x, int y){
		brushPixel = current.getPixel(x,y);
	}
	public Pixel getBrushPixel(){
		return brushPixel;  
	}

	public void paintPop(){
		if(!paintStack.isEmpty()){
			if(!mouseDown){
				Frame undid = paintStack.pop();
				for(int i=0;i<undid.getWidth();i++){
					for (int j=0;j<undid.getHeight();j++){
						Pixel p = undid.getPixel(i, j);
						current.setPixel(i, j, p);
					}
				}
			}
		}
	}
	public void setMouseUp(){
		mouseDown=false;
	}
}
