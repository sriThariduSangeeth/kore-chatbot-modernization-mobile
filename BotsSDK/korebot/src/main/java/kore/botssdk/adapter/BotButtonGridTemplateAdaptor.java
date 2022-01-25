package kore.botssdk.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import kore.botssdk.R;
import kore.botssdk.models.BotButtonModel;

import kore.botssdk.application.AppControl;
import kore.botssdk.listener.ComposeFooterInterface;
import kore.botssdk.listener.InvokeGenericWebViewInterface;
import kore.botssdk.models.BotResponse;
import kore.botssdk.net.SDKConfiguration;
import kore.botssdk.utils.BundleConstants;
import androidx.recyclerview.widget.RecyclerView;

public class BotButtonGridTemplateAdaptor extends BaseAdapter {

    List<BotButtonModel> botButtonModels = new ArrayList<>();
    private final String splashColour;
    private final String disabledColour;
    private final String textColor;
    private final String disabledTextColor;
    private LayoutInflater ownLayoutInflater = null;
    private boolean isEnabled;
    private float dp1;
    private SharedPreferences sharedPreferences;

    private ComposeFooterInterface composeFooterInterface;
    private InvokeGenericWebViewInterface invokeGenericWebViewInterface;

    public BotButtonGridTemplateAdaptor(Context context){
        ownLayoutInflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences(BotResponse.THEME_NAME, Context.MODE_PRIVATE);

        splashColour = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.colorPrimary));
        disabledColour = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.meetingsDisabled));
        textColor = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.white));
        disabledTextColor = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.white));
    }


    @Override
    public int getCount() {
        if(botButtonModels != null){
            return botButtonModels.size();
        }
        return 0;
    }

    @Override
    public BotButtonModel getItem(int i) {
        return botButtonModels.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ownLayoutInflater.inflate(R.layout.bot_button_grid_layout, null);
        }

        if (view.getTag() == null) {
            initializeViewHolder(view);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        populateView(holder, position);

        return view;
    }

    private void populateView(ViewHolder holder, int position) {
        BotButtonModel buttonTemplate = getItem(position);

        holder.botItemButton.setText(buttonTemplate.getTitle());

        holder.botItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (composeFooterInterface != null && invokeGenericWebViewInterface != null && isEnabled()) {

                    if (BundleConstants.BUTTON_TYPE_WEB_URL.equalsIgnoreCase(buttonTemplate.getType())) {
                        invokeGenericWebViewInterface.invokeGenericWebView(buttonTemplate.getUrl());
                    }
                    else if(BundleConstants.BUTTON_TYPE_URL.equalsIgnoreCase(buttonTemplate.getType())) {
                        invokeGenericWebViewInterface.invokeGenericWebView(buttonTemplate.getUrl());
                    }else if(BundleConstants.BUTTON_TYPE_USER_INTENT.equalsIgnoreCase(buttonTemplate.getType())){
                        setEnabled(false);
                        invokeGenericWebViewInterface.handleUserActions(buttonTemplate.getAction(),buttonTemplate.getCustomData());
                    }else{
                        setEnabled(false);
                        String title = buttonTemplate.getTitle();
                        String payload = buttonTemplate.getPayload();
                        composeFooterInterface.onSendClick(title, payload,false);
                    }
                }
            }
        });
    }

    public void setComposeFooterInterface(ComposeFooterInterface composeFooterInterface) {
        this.composeFooterInterface = composeFooterInterface;
    }

    public void setInvokeGenericWebViewInterface(InvokeGenericWebViewInterface invokeGenericWebViewInterface) {
        this.invokeGenericWebViewInterface = invokeGenericWebViewInterface;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public static class ViewHolder {
        TextView botItemButton;
    }

    private void initializeViewHolder(View view) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.botItemButton = (TextView) view.findViewById(R.id.grid_text);

//        ((GradientDrawable) viewHolder.botItemButton.getBackground()).setColor(isEnabled ? Color.parseColor(splashColour) : Color.parseColor(disabledColour));
        ((GradientDrawable) viewHolder.botItemButton.getBackground()).setStroke((int)(2*dp1), isEnabled ? Color.parseColor(splashColour) : Color.parseColor(disabledColour));
        viewHolder.botItemButton.setTextColor(isEnabled ? Color.parseColor(splashColour) : Color.parseColor(disabledColour));
        view.setTag(viewHolder);
    }

    public void setBotButtonModels(ArrayList<BotButtonModel> botButtonModels) {
        this.botButtonModels = botButtonModels;
    }

}
