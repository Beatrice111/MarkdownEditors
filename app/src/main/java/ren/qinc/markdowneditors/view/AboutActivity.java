/*
 * Copyright 2016. SHENQINCI(沈钦赐)<946736079@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ren.qinc.markdowneditors.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import ren.qinc.markdowneditors.R;
import ren.qinc.markdowneditors.base.BaseActivity;
import ren.qinc.markdowneditors.base.BaseToolbarActivity;
import ren.qinc.markdowneditors.base.BaseWebActivity;
import ren.qinc.markdowneditors.utils.SystemBarUtils;
import ren.qinc.markdowneditors.utils.SystemUtils;

/**
 * about
 * Created by 沈钦赐 on 16/6/30.
 */
public class AboutActivity extends BaseToolbarActivity {
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.description)
    TextView description;
    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    public static void startAboutActivity(Context context){
        Intent intent = new Intent(context,AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    public void onCreateAfter(Bundle savedInstanceState) {
        version.setText(String.format(getString(R.string.version_string), SystemUtils.getAppVersion(mContext)));
        String fromAssets = SystemUtils.getAssertString(mContext.getApplicationContext(),"description.txt");
        if(TextUtils.isEmpty(fromAssets)){
            description.setText("MarkdownEditors");
        }else{

            description.setText(fromAssets);
        }
    }

    @Override
    protected void initStatusBar() {
        SystemBarUtils.tintStatusBar(this,getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.contact_me)
    protected void contackMe(){
        Uri uri = Uri.parse("mailto:qq@qinci.me");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
        // intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
        // intent.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
        this.startActivity(intent);
//        Intent.createChooser(intent, "请选择邮件类应用")
    }


    @OnClick(R.id.about_github)
    protected void openSource(){
        BaseWebActivity.loadUrl(this,"https://github.com/qinci/MarkdownEditors","源码地址");
    }

    @NonNull
    @Override
    protected String getTitleString() {
        return this.getString(R.string.about);
    }
}
