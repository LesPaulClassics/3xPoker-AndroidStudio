package com.tsop.android.triplePlayPoker;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ShowSplashScreen extends Activity {

//    private SoundPool soundPool; 
//    private HashMap<Sounds, Integer> soundPoolMap; 
	private MediaPlayer mp;

	protected boolean _active = true;
	protected int _splashTime = 3600; // time to display the splash screen in ms
	
	private Animation anm3Translate;
	private Animation anmXTranslate;
	private Animation anmPTranslate;
	private Animation anmOTranslate;
	private Animation anmKTranslate;
	private Animation anmETranslate;
	private Animation anmRTranslate;
	
	private Animation anmFlipOver[], anmFlipBack[];
	private Animation anmFadeIn;

	private ImageView img3Card;
	private ImageView imgXCard;
	private ImageView imgPCard;
	private ImageView imgOCard;
	private ImageView imgKCard;
	private ImageView imgECard;
	private ImageView imgRCard;
	private ImageView[] imgCards;
	private ImageView imgTSOP;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        if(settings.getBoolean(Constants.SOUND_ON_PREF, true))
        {
            Thread musicThread = new Thread() {
                @Override
                public void run() {
                    	mp = MediaPlayer.create(getApplicationContext(), R.raw.splash);
                    	mp.start();
//                        mp.release();
                    }
            };
            musicThread.start();
        }

        imgTSOP = (ImageView) findViewById(R.id.imgTSOP);
        imgTSOP.setVisibility(4);
        anm3Translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.title3translate);
        anmXTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.titlextranslate);
        anmPTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.titleptranslate);
        anmOTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.titleotranslate);
        anmKTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.titlektranslate);
        anmETranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.titleetranslate);
        anmRTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.titlertranslate);
        anmRTranslate.setAnimationListener(new FinishDeal());

        anmFlipOver = new Animation[7];
        anmFlipBack = new Animation[7];
        for (int i=0; i < 7; i++){
        	anmFlipOver[i] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_flip_over);
        	anmFlipOver[i].setStartOffset(50 + (i * 50));
            anmFlipBack[i] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_flip_back);
            anmFlipBack[i].setFillEnabled(true);
            anmFlipBack[i].setFillBefore(true);
        }
        
        anmFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

        img3Card = (ImageView)findViewById(R.id.imgTitle3);
        imgXCard = (ImageView)findViewById(R.id.imgTitleX);
        imgPCard = (ImageView)findViewById(R.id.imgTitleP);
        imgOCard = (ImageView)findViewById(R.id.imgTitleO);
        imgKCard = (ImageView)findViewById(R.id.imgTitleK);
        imgECard = (ImageView)findViewById(R.id.imgTitleE);
        imgRCard = (ImageView)findViewById(R.id.imgTitleR);
        
        imgCards = new ImageView[] {img3Card, imgXCard, imgPCard, imgOCard, imgKCard, imgECard, imgRCard};
        	
        // thread for displaying the SplashScreen
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                	img3Card.startAnimation(anm3Translate);
                	imgXCard.startAnimation(anmXTranslate);
                	imgPCard.startAnimation(anmPTranslate);
                	imgOCard.startAnimation(anmOTranslate);
                	imgKCard.startAnimation(anmKTranslate);
                	imgECard.startAnimation(anmETranslate);
                	imgRCard.startAnimation(anmRTranslate);
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(new Intent("com.tsop.android.triplePlayPoker.ShowGameBoard"));
//                    stop();
//                    soundPool.release();
                    if (mp != null)
                    	mp.release();
                }
            }
        };
        splashThread.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if (event.getAction() == MotionEvent.ACTION_DOWN) {
	        _active = false;
	    }
	    return true;
	}
	
    /**
     * This class listens for the end of the first half of the card flip animation.
     * It then changes the card to the correct image and finishes the flip
     */
    private final class FinishDeal implements Animation.AnimationListener {
 
        private FinishDeal() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        	
       		imgTSOP.setVisibility(0);
       		imgTSOP.startAnimation(anmFadeIn);

    		Drawable[] newCards = new Drawable[] {
    				getResources().getDrawable(R.drawable.titlecard3),
    				getResources().getDrawable(R.drawable.titlecardx),
					getResources().getDrawable(R.drawable.titlecardp),
					getResources().getDrawable(R.drawable.titlecardo),
					getResources().getDrawable(R.drawable.titlecardk),
					getResources().getDrawable(R.drawable.titlecarde),
					getResources().getDrawable(R.drawable.titlecardr)
    		};
        	for (int i=0;i < 7; i++)
        	{
				anmFlipOver[i].setAnimationListener(new FinishFlip(i, newCards[i]));
        		anmFlipOver[i].setFillEnabled(true);
        		anmFlipOver[i].setFillAfter(true);
				imgCards[i].startAnimation(anmFlipOver[i]);
        	}

        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
    
    /**
     * This class listens for the end of the first half of the card flip animation.
     * It then changes the card to the correct image and finishes the flip
     */
    private final class FinishFlip implements Animation.AnimationListener {
 
        private final int cardIndex;
        private final Drawable cardImage;

        private FinishFlip(int index, Drawable newCard) {
            cardIndex = index;
            cardImage = newCard;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        	imgCards[cardIndex].setImageDrawable(cardImage);
        	imgCards[cardIndex].startAnimation(anmFlipBack[cardIndex]);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
}
