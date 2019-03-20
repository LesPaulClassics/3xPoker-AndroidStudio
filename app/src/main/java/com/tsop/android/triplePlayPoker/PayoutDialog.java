package com.tsop.android.triplePlayPoker;

import java.util.ArrayList;

import com.tsop.android.triplePlayPoker.R.id;
import com.tsop.games.triplePlay.Calculator;
import com.tsop.games.triplePlay.Games;
import com.tsop.games.triplePlay.WinningHand;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

public class PayoutDialog extends Dialog {

	public PayoutDialog(Context context) {
		super(context);

	}
	
	private TextView[][] table;
	private TableRow[] rows;
	private Button btnClose;
	private Games currentGame;
	
    public void setCurrentGame(Games game)
    {
    	currentGame = game;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payouttable);
        setTitle(Calculator.GetGameName(currentGame));
        
        setCanceledOnTouchOutside(true);
        
        btnClose = (Button)findViewById(id.btnClosePayoutDlg);
        btnClose.setOnClickListener(new CloseListener());
        
        rows = new TableRow[] {
        		(TableRow)findViewById(id.TableRow03),
        		(TableRow)findViewById(id.TableRow04),
        		(TableRow)findViewById(id.TableRow05),
        		(TableRow)findViewById(id.TableRow06),
        		(TableRow)findViewById(id.TableRow07),
        		(TableRow)findViewById(id.TableRow08),
        		(TableRow)findViewById(id.TableRow09),
        		(TableRow)findViewById(id.TableRow10),
        		(TableRow)findViewById(id.TableRow11),
        		(TableRow)findViewById(id.TableRow12),
        		(TableRow)findViewById(id.TableRow13),
        		(TableRow)findViewById(id.TableRow14),
        };
      
        table = new TextView[][]{
        		{
        			(TextView)findViewById(id.txtPayoutRank12),
        			(TextView)findViewById(id.txtPayoutRank12_1),
        			(TextView)findViewById(id.txtPayoutRank12_2),
        			(TextView)findViewById(id.txtPayoutRank12_3),
        			(TextView)findViewById(id.txtPayoutRank12_4),
        			(TextView)findViewById(id.txtPayoutRank12_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank11),
        			(TextView)findViewById(id.txtPayoutRank11_1),
        			(TextView)findViewById(id.txtPayoutRank11_2),
        			(TextView)findViewById(id.txtPayoutRank11_3),
        			(TextView)findViewById(id.txtPayoutRank11_4),
        			(TextView)findViewById(id.txtPayoutRank11_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank10),
        			(TextView)findViewById(id.txtPayoutRank10_1),
        			(TextView)findViewById(id.txtPayoutRank10_2),
        			(TextView)findViewById(id.txtPayoutRank10_3),
        			(TextView)findViewById(id.txtPayoutRank10_4),
        			(TextView)findViewById(id.txtPayoutRank10_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank9),
        			(TextView)findViewById(id.txtPayoutRank9_1),
        			(TextView)findViewById(id.txtPayoutRank9_2),
        			(TextView)findViewById(id.txtPayoutRank9_3),
        			(TextView)findViewById(id.txtPayoutRank9_4),
        			(TextView)findViewById(id.txtPayoutRank9_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank8),
        			(TextView)findViewById(id.txtPayoutRank8_1),
        			(TextView)findViewById(id.txtPayoutRank8_2),
        			(TextView)findViewById(id.txtPayoutRank8_3),
        			(TextView)findViewById(id.txtPayoutRank8_4),
        			(TextView)findViewById(id.txtPayoutRank8_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank7),
        			(TextView)findViewById(id.txtPayoutRank7_1),
        			(TextView)findViewById(id.txtPayoutRank7_2),
        			(TextView)findViewById(id.txtPayoutRank7_3),
        			(TextView)findViewById(id.txtPayoutRank7_4),
        			(TextView)findViewById(id.txtPayoutRank7_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank6),
        			(TextView)findViewById(id.txtPayoutRank6_1),
        			(TextView)findViewById(id.txtPayoutRank6_2),
        			(TextView)findViewById(id.txtPayoutRank6_3),
        			(TextView)findViewById(id.txtPayoutRank6_4),
        			(TextView)findViewById(id.txtPayoutRank6_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank5),
        			(TextView)findViewById(id.txtPayoutRank5_1),
        			(TextView)findViewById(id.txtPayoutRank5_2),
        			(TextView)findViewById(id.txtPayoutRank5_3),
        			(TextView)findViewById(id.txtPayoutRank5_4),
        			(TextView)findViewById(id.txtPayoutRank5_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank4),
        			(TextView)findViewById(id.txtPayoutRank4_1),
        			(TextView)findViewById(id.txtPayoutRank4_2),
        			(TextView)findViewById(id.txtPayoutRank4_3),
        			(TextView)findViewById(id.txtPayoutRank4_4),
        			(TextView)findViewById(id.txtPayoutRank4_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank3),
        			(TextView)findViewById(id.txtPayoutRank3_1),
        			(TextView)findViewById(id.txtPayoutRank3_2),
        			(TextView)findViewById(id.txtPayoutRank3_3),
        			(TextView)findViewById(id.txtPayoutRank3_4),
        			(TextView)findViewById(id.txtPayoutRank3_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank2),
        			(TextView)findViewById(id.txtPayoutRank2_1),
        			(TextView)findViewById(id.txtPayoutRank2_2),
        			(TextView)findViewById(id.txtPayoutRank2_3),
        			(TextView)findViewById(id.txtPayoutRank2_4),
        			(TextView)findViewById(id.txtPayoutRank2_5)
        		},
        		{
        			(TextView)findViewById(id.txtPayoutRank1),
        			(TextView)findViewById(id.txtPayoutRank1_1),
        			(TextView)findViewById(id.txtPayoutRank1_2),
        			(TextView)findViewById(id.txtPayoutRank1_3),
        			(TextView)findViewById(id.txtPayoutRank1_4),
        			(TextView)findViewById(id.txtPayoutRank1_5)
        		}
        };
        
//        for (int handIndex=0; handIndex < WinningHands.values().length - 1; handIndex++)
//        {
//        	WinningHands hand = WinningHands.values()[WinningHands.values().length - handIndex - 1];
//        	for (int bet=0; bet < 5; bet++)
//        	{
//        		table[handIndex][bet].setText(Integer.toString(hand.getPayout() * (bet+1)));
//        	}
//        	
//        }
        
        ArrayList<WinningHand> winningHands = Calculator.getWinningHands(currentGame);
        
        for (int handIndex=0; handIndex < winningHands.size() - 1; handIndex++)
        {
        	WinningHand hand = winningHands.get(winningHands.size() - handIndex - 1);
        	table[handIndex][0].setText(hand.toString());
        	for (int bet=0; bet < 5; bet++)
        	{
        		table[handIndex][bet + 1].setText(Integer.toString(hand.getPayout() * (bet+1)));
        	}
        	
        }
        
        // make the remaining rows "blank"
        for (int rowIndex = winningHands.size() - 1; rowIndex < table.length; rowIndex++)
        {
        	rows[rowIndex].setBackgroundColor(0);
        	for (int col=0; col < 6; col++)
        	{
//        		table[rowIndex][col].setBackgroundColor(R.color.Green);
        		table[rowIndex][col].setText("");
        	}
        }


    }
    
    private class CloseListener implements android.view.View.OnClickListener {

		public void onClick(View arg0) {
			PayoutDialog.this.dismiss();
			
		}
    }
    
}
