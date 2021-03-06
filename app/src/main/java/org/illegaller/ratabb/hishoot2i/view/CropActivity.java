package org.illegaller.ratabb.hishoot2i.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import javax.inject.Inject;
import org.illegaller.ratabb.hishoot2i.R;
import org.illegaller.ratabb.hishoot2i.di.compenent.ActivityComponent;
import org.illegaller.ratabb.hishoot2i.presenter.CropActivityPresenter;
import org.illegaller.ratabb.hishoot2i.view.common.BaseActivity;
import org.illegaller.ratabb.hishoot2i.view.widget.CropImageView;

public class CropActivity extends BaseActivity implements CropActivityView {
  private static final String KEY_PATH_IMAGE = "path_image";
  private static final String KEY_POINT_RATIO = "point_ratio";
  @InjectExtra(KEY_PATH_IMAGE) String pathImage;
  @InjectExtra(KEY_POINT_RATIO) Point pointRatio;
  @BindView(R.id.cropImageVIew) CropImageView mCropImageView;
  @BindView(R.id.pbCrop) ProgressBar mProgressBar;
  @BindView(R.id.btnOkCrop) View mBtnOk;
  @BindView(R.id.btnCancelCrop) View mBtnCancel;
  @Inject CropActivityPresenter presenter;

  public static Intent getIntent(Context context, String path, Point ratio) {
    Intent starter = new Intent(context, CropActivity.class);
    starter.putExtra(KEY_PATH_IMAGE, path);
    starter.putExtra(KEY_POINT_RATIO, ratio);
    starter.putExtra(Intent.EXTRA_RETURN_RESULT, true);
    return starter;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);
    presenter.attachView(this);
    presenter.initView();
  }

  @Override protected void injectComponent(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @Override protected int getToolbarId() {
    return View.NO_ID;
  }

  @Override protected int layoutRes() {
    return R.layout.activity_crop;
  }

  @Override protected void setupToolbar(ActionBar actionBar) { /*no-op*/ }

  @Override public CropImageView getCropImageView() {
    return mCropImageView;
  }

  @Override public View getViewBtnOk() {
    return mBtnOk;
  }

  @Override public View getViewBtnCancel() {
    return mBtnCancel;
  }

  @Override public void showProgress(boolean isShow) {
    mProgressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    mCropImageView.setVisibility(isShow ? View.GONE : View.VISIBLE);
  }

  @Override public Point getPointRatio() {
    return pointRatio;
  }

  @Override public String getPathImage() {
    return pathImage;
  }

  @Override public Context getContext() {
    return this;
  }
}
