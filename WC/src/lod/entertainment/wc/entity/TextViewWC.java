package lod.entertainment.wc.entity;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewWC extends TextView {

	public TextViewWC(Context context) {
		super(context);
		Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
				"font/brasil_wc.otf");
		setTypeface(typeFace);
	}

	public TextViewWC(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
				"font/brasil_wc.otf");
		setTypeface(typeFace);
	}
	
	public TextViewWC(Context context, AttributeSet attrs) {
		super(context, attrs);
		Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
				"font/brasil_wc.otf");
		setTypeface(typeFace);
	}
	
}
