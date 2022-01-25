package kore.botssdk.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import kore.botssdk.R;
import kore.botssdk.listener.ComposeFooterInterface;
import kore.botssdk.listener.InvokeGenericWebViewInterface;
import kore.botssdk.models.BotButtonModel;
import kore.botssdk.models.BotResponse;
import kore.botssdk.utils.BundleConstants;

public class BotButtonStaggeredTemplateAdaptor extends RecyclerView.Adapter<BotButtonStaggeredTemplateAdaptor.ViewHolder> {

    List<BotButtonModel> botButtonModels = new LinkedList<>();
    private final String splashColour;
    private final String disabledColour;
    private LayoutInflater ownLayoutInflater = null;
    private final String textColor;
    private final String disabledTextColor;
    private boolean isEnabled;
    private float dp1;
    private SharedPreferences sharedPreferences;

    private ComposeFooterInterface composeFooterInterface;
    private InvokeGenericWebViewInterface invokeGenericWebViewInterface;

    public BotButtonStaggeredTemplateAdaptor(Context context) {
        ownLayoutInflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences(BotResponse.THEME_NAME, Context.MODE_PRIVATE);

        splashColour = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.colorPrimary));
        disabledColour = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.meetingsDisabled));
        textColor = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.white));
        disabledTextColor = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.white));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View view = ownLayoutInflater.inflate(R.layout.bot_button_grid_layout, parent, false);

        BotButtonStaggeredTemplateAdaptor.ViewHolder holder = new ViewHolder(view, this);
        if (view.getTag() == null) {
            initializeViewHolder(view, holder);
        }
        populateView(holder, position);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        populateView(holder, position);
    }

    @Override
    public int getItemCount() {
        if (botButtonModels != null) {
            return botButtonModels.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView botItemButton = null;
        final BotButtonStaggeredTemplateAdaptor mAdapter;


        public ViewHolder(@NonNull View itemView, BotButtonStaggeredTemplateAdaptor mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
        }
    }

    private void initializeViewHolder(View view, BotButtonStaggeredTemplateAdaptor.ViewHolder viewHolder) {
        viewHolder.botItemButton = view.findViewById(R.id.grid_text);

//        ((GradientDrawable) viewHolder.botItemButton.getBackground()).setColor(isEnabled ? Color.parseColor(splashColour) : Color.parseColor(disabledColour));
        ((GradientDrawable) viewHolder.botItemButton.getBackground()).setStroke((int) (2 * dp1), isEnabled ? Color.parseColor(splashColour) : Color.parseColor(disabledColour));
        viewHolder.botItemButton.setTextColor(isEnabled ? Color.parseColor(splashColour) : Color.parseColor(disabledColour));
        view.setTag(viewHolder);
    }

    private void populateView(BotButtonStaggeredTemplateAdaptor.ViewHolder holder, int position) {
        BotButtonModel buttonTemplate = this.botButtonModels.get(position);

        holder.botItemButton.setText(buttonTemplate.getTitle());

        holder.botItemButton.setOnClickListener(v -> {
            if (composeFooterInterface != null && invokeGenericWebViewInterface != null && isEnabled()) {

                if (BundleConstants.BUTTON_TYPE_WEB_URL.equalsIgnoreCase(buttonTemplate.getType())) {
                    invokeGenericWebViewInterface.invokeGenericWebView(buttonTemplate.getUrl());
                } else if (BundleConstants.BUTTON_TYPE_URL.equalsIgnoreCase(buttonTemplate.getType())) {
                    invokeGenericWebViewInterface.invokeGenericWebView(buttonTemplate.getUrl());
                } else if (BundleConstants.BUTTON_TYPE_USER_INTENT.equalsIgnoreCase(buttonTemplate.getType())) {
                    setEnabled(false);
                    invokeGenericWebViewInterface.handleUserActions(buttonTemplate.getAction(), buttonTemplate.getCustomData());
                } else {
                    setEnabled(false);
                    String title = buttonTemplate.getTitle();
                    String payload = buttonTemplate.getPayload();
                    composeFooterInterface.onSendClick(title, payload, false);
                }
            }
        });
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<BotButtonModel> getBotButtonModels() {
        return botButtonModels;
    }

    public void setBotButtonModels(List<BotButtonModel> botButtonModels) {
        this.botButtonModels = botButtonModels;
    }

    public ComposeFooterInterface getComposeFooterInterface() {
        return composeFooterInterface;
    }

    public void setComposeFooterInterface(ComposeFooterInterface composeFooterInterface) {
        this.composeFooterInterface = composeFooterInterface;
    }

    public InvokeGenericWebViewInterface getInvokeGenericWebViewInterface() {
        return invokeGenericWebViewInterface;
    }

    public void setInvokeGenericWebViewInterface(InvokeGenericWebViewInterface invokeGenericWebViewInterface) {
        this.invokeGenericWebViewInterface = invokeGenericWebViewInterface;
    }
}
