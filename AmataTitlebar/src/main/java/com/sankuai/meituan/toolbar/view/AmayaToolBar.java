package com.sankuai.meituan.toolbar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.sankuai.meituan.bean.AmayaItem;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午7:13
 */
public class AmayaToolBar extends FrameLayout implements View.OnClickListener {
    private EditText amayaSearchView;
    private ProgressBar amayaLoading;
    private LinearLayout itemLayout;
    private AmayaMenuClickListener listener;
    private LinearLayout.LayoutParams itemLP;
    private LinearLayout amayaTitleLayout;
    private int itemSelector = R.drawable.amaya_item_bg_selector;
    private PopupWindow popupWindow;
    private int popWindowBg = R.drawable.actionbar_bg_pop_defaut;
    private int amayaPopAnimStyle = R.style.AmayaPopupAnimation;
    private LinearLayout popupLayout;

    public AmayaToolBar(Context context) {
        super(context);
        initView(context);
    }

    public AmayaToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AmayaToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyles(context, attrs);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.L)
    public AmayaToolBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initStyles(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        int eachDimenHeight = (int) getResources().getDimension(R.dimen.amaya_title_bar_height);
        /**
         * 第一层， Toolbar
         */
        amayaTitleLayout = generateTitleLayout(context, eachDimenHeight - 10);
        FrameLayout.LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        llp.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        addView(amayaTitleLayout, llp);

        /**
         * 第二层， 搜索框
         */
        amayaSearchView = new EditText(context);
        addView(amayaSearchView);
        showSearchView(false);

        /**
         * 第三层， Item
         */
        itemLayout = new LinearLayout(context);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams ilp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ilp.gravity = Gravity.RIGHT;
        itemLayout.setPadding(5, 5, 5, 5);
        addView(itemLayout, ilp);
        itemLP = new LinearLayout.LayoutParams(eachDimenHeight, eachDimenHeight);
        itemLP.gravity = Gravity.CENTER_VERTICAL;

        /**
         * 第四层， 缓冲框
         */
        amayaLoading = new ProgressBar(context);
        amayaLoading.setIndeterminateDrawable(getResources().getDrawable(R.drawable.abs__progress_medium_holo));
//        amayaLoading = new AmayaProgressBar(context, null);
        FrameLayout.LayoutParams flp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        flp.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        flp.rightMargin = 3;
        addView(amayaLoading,flp);


    }

    private void initStyles(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AmayaToolBar);
        itemSelector = a.getResourceId(R.styleable.AmayaToolBar_itemSelector, R.drawable.amaya_item_bg_selector);
        a.recycle();
    }

    private void showSearchView(boolean show) {
        amayaSearchView.setVisibility(show?VISIBLE:GONE);
    }


    public void showLoading(boolean show) {
        if(show && isShowLoading()) return;
        amayaLoading.setVisibility(show?VISIBLE:GONE);
        itemLayout.setVisibility(show ? GONE : VISIBLE);
        if (popupWindow != null && popupWindow.isShowing()) popupWindow.dismiss();
    }

    private boolean isShowLoading() {
        return amayaLoading.getVisibility() ==VISIBLE;
    }

    /**
     * 增加右上角Item
     *
     * @param item         封装的item对象
     * @param replaceIndex 替换的inex位置
     */
    public void addItem(AmayaItem item, int replaceIndex) {
        int count = itemLayout.getChildCount();
        if (item.imgRes != 0) {
            ImageView img = null;
            if (count == 0 || replaceIndex >= count) {
                if (count == 3) {
                    View child3 = itemLayout.getChildAt(2);
                    int id = child3.getId();
                    if (id != R.id.amaya_menu_more) {
                        if (child3 instanceof ImageView) {
                            ((ImageView) child3).setImageResource(R.drawable.amaya_menu_more);
                            child3.setId(R.id.amaya_menu_more);
                        } else {
                            itemLayout.removeView(child3);
                            ImageView moreItem = new ImageView(getContext());
                            moreItem.setImageResource(R.drawable.amaya_menu_more);
                            moreItem.setId(R.id.amaya_menu_more);
                            itemLayout.addView(child3, 2);
                        }
                        AmayaItem item1 = (AmayaItem) child3.getTag();
                        addMoreItem(item1);
                    }
                    addMoreItem(item);
                    return;
                } else {
                    img = new ImageView(getContext());
                    img.setBackgroundResource(itemSelector);
                    img.setTag(item);
                    itemLayout.addView(img, itemLP);
                }

            } else if (replaceIndex >= 0 && replaceIndex < count) {
                View v = itemLayout.getChildAt(replaceIndex);
                if (v instanceof ImageView) {
                    img = (ImageView) v;
                } else {
                    img = new ImageView(getContext());
                    itemLayout.addView(img, replaceIndex, itemLP);
                    itemLayout.removeView(v);
                }
            }
            img.setTag(item);
            img.setImageResource(item.imgRes);
            img.setId(item.itemId);
            img.setOnClickListener(this);
        } else {
            TextView img = new TextView(getContext());
            img.setText(item.txtRes);
            img.setId(item.itemId);
            img.setTag(item);
            img.setOnClickListener(this);
            itemLayout.removeAllViewsInLayout();
            itemLayout.addView(img);
        }

    }


    /**
     * 设置MenuItem背景资源
     *
     * @param drawable
     */
    public void setItemSelector(int drawable) {
        itemSelector = drawable;
        amayaTitleLayout.setBackgroundResource(drawable);
        int count = itemLayout.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                itemLayout.getChildAt(i).setBackgroundResource(drawable);
            }
        }

        if (popupWindow != null) {
            int popCount = popupLayout.getChildCount();
            for (int i = 0; i < popCount; i++) {
                popupLayout.getChildAt(i).setBackgroundResource(drawable);
            }
        }
//        findViewById(R.id.amaya_title).setBackgroundResource(drawable);
    }

    /**
     * MenuItem回调接口
     *
     * @param listener
     */
    public void addAmayaItemClick(AmayaMenuClickListener listener) {
        this.listener = listener;
    }

    /**
     * 清楚MenuItem
     */
    public void clearItem() {
        itemLayout.removeAllViews();
        if (popupWindow != null) {
            if (popupWindow.isShowing()) popupWindow.dismiss();
            popupLayout.removeAllViews();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.amaya_menu_more) {
            togglePopwindowView();
        } else {
            if (popupWindow != null && popupWindow.isShowing()) popupWindow.dismiss();
            if (listener != null) {
                listener.onAmayaItemClick(v.getId());
            }
        }

    }

    private void togglePopwindowView() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            View childAt = itemLayout.getChildAt(0);
            popupWindow.showAsDropDown(childAt);
        }
    }

    private LinearLayout generateTitleLayout(Context context, int eachDimenHeight) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(5, 5, 5, 5);
        ll.setGravity(Gravity.CENTER_VERTICAL);

        /**
         * 设置返回icon
         */
        ImageView backImg = new ImageView(context);
        backImg.setImageResource(R.drawable.amaya_title_back);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(24, 24);
        ll.addView(backImg, lp1);
        /**
         * 设置Logo
         */
        ImageView logo = new ImageView(context);
        logo.setImageResource(R.drawable.ic_launcher);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LinearLayout.LayoutParams logoLP = new LinearLayout.LayoutParams(eachDimenHeight, eachDimenHeight);
        int margin = (int) (getResources().getDisplayMetrics().density * 10);
        logoLP.topMargin = logoLP.bottomMargin = margin;
        ll.addView(logo, logoLP);
        /**
         * 设置标题
         */
        TextView title = new TextView(context);
        title.setText(R.string.app_name);
        title.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        title.setTextColor(Color.BLACK);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        ll.addView(title);


        ll.setId(R.id.amaya_title);
        ll.setBackgroundResource(itemSelector);
        ll.setOnClickListener(this);
        return ll;
    }

    /**
     * 设置标题左侧返回箭头显示与隐藏
     *
     * @param visible true:显示；false:隐藏
     */
    public void setBackViewVisible(boolean visible) {
        if (amayaTitleLayout == null) return;
        amayaTitleLayout.getChildAt(0).setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置标题左侧Logo图标
     *
     * @param drawable 资源ID
     */
    public void setLogo(int drawable) {
        if (amayaTitleLayout == null) return;
        ImageView logo = ((ImageView) amayaTitleLayout.getChildAt(1));
        logo.setImageResource(drawable);
        if (logo.getVisibility() == GONE) {
            logo.setVisibility(VISIBLE);
        }
    }

    /**
     * 设置标题左侧Logo图标显示与隐藏
     *
     * @param visible true:显示；false:隐藏
     */
    public void setLogoVisible(boolean visible) {
        if (amayaTitleLayout == null) return;
        amayaTitleLayout.getChildAt(1).setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(int title) {
        if (amayaTitleLayout == null) return;
        ((TextView) amayaTitleLayout.getChildAt(2)).setText(title);
    }

    /**
     * 设置标题颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        if (amayaTitleLayout == null) return;
        ((TextView) amayaTitleLayout.getChildAt(2)).setTextColor(color);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (amayaTitleLayout == null) return;
        ((TextView) amayaTitleLayout.getChildAt(2)).setText(title);
    }

    /**
     * 设置PopupWindow背景资源
     *
     * @param drawable
     */
    public void setPopWindowBg(int drawable) {
        popWindowBg = drawable;
        if (popupLayout != null) {
            popupLayout.setBackgroundResource(drawable);
        }
    }

    /**
     * 设置弹出菜单AnimaionStyle
     *
     * @param animStyle
     */
    public void setPopupWindowAnim(int animStyle) {
        amayaPopAnimStyle = animStyle;
        if (popupWindow != null) popupWindow.setAnimationStyle(amayaPopAnimStyle);
    }

    private void addMoreItem(AmayaItem item) {
        item.setShowImg(true);
        if (popupWindow == null) {
            ScrollView sv = new ScrollView(getContext());
            popupLayout = new LinearLayout(getContext());
            popupLayout.setBackgroundResource(popWindowBg);
            popupLayout.setOrientation(LinearLayout.VERTICAL);
            ScrollView.LayoutParams slp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            sv.addView(popupLayout);
            popupWindow = new PopupWindow(sv, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
            popupWindow.setAnimationStyle(amayaPopAnimStyle);
        }
        AmayaItemView itemView = (AmayaItemView) popupLayout.findViewById(item.itemId);
        if (itemView == null) {
            itemView = new AmayaItemView(getContext(), item, itemSelector);
            itemView.setId(item.itemId);
            popupLayout.addView(itemView);
        } else {
            itemView.updateView(item);
        }
        if (popupLayout.getChildCount() == 5) {
            ScrollView sv = (ScrollView) popupWindow.getContentView();
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) sv.getLayoutParams();
            int height = popupLayout.getHeight();
            Log.e("amaya", "params =" + params + "--397...height=" + height + "--" + popupLayout.getHeight());
            if (height == 0) height = (int) (getResources().getDimension(R.dimen.amaya_title_bar_height) * 6);
            if (params == null) {
                params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, height);
            } else {
                params.height = height;
            }
            sv.setLayoutParams(params);
            Log.e("amaya", "params =" + params + "--402...height=" + height + "--" + popupLayout.getHeight());
        }
        itemView.setOnClickListener(this);
    }

}
