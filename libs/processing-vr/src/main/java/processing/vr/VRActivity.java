/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2016-19 The Processing Foundation

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation, version 2.1.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/

package processing.vr;

import android.content.Intent;
import android.util.DisplayMetrics;

// This will give a "Cannot resolve symbol 'base'" error in Android Studio because it cannot get
// the classes from inside the local .aar files for google-vr. But any VR app runs and can also be debugged.
import com.google.vr.sdk.base.GvrActivity;

import processing.android.AppComponent;
import processing.android.ServiceEngine;
import processing.core.PApplet;

public class VRActivity extends GvrActivity implements AppComponent {
  static public final int GVR = 3;

  private DisplayMetrics metrics;
  private PApplet sketch;


  public VRActivity() {

  }


  static public VRGraphics getRenderer(PApplet p) {
    return (VRGraphics) p.g;
  }


  public VRActivity(PApplet sketch) {
    this.sketch = sketch;
  }


  public void initDimensions() {
    metrics = getResources().getDisplayMetrics();
  }


  public int getDisplayWidth() {
    return metrics.widthPixels;
  }


  public int getDisplayHeight() {
    return metrics.heightPixels;
  }


  public float getDisplayDensity() {
    return metrics.density;
  }


  public int getKind() {
      return GVR;
  }


  public void dispose() {
  }


  public void setSketch(PApplet sketch) {
    this.sketch = sketch;
    if (sketch != null) {
      sketch.initSurface(VRActivity.this, null);
      // Required to read the paired viewer's distortion parameters.
      sketch.requestPermission("android.permission.READ_EXTERNAL_STORAGE");
    }
  }


  public PApplet getSketch() {
    return sketch;
  }


  public boolean isService() {
    return false;
  }


  public ServiceEngine getEngine() {
    return null;
  }


  @Override
  public void onResume() {
    super.onResume();
    if (sketch != null) {
      sketch.onResume();
    }
  }


  @Override
  public void onPause() {
    super.onPause();
    if (sketch != null) {
      sketch.onPause();
    }
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    if (sketch != null) {
      sketch.onDestroy();
    }
  }


  @Override
  public void onStart() {
    super.onStart();
    if (sketch != null) {
      sketch.onStart();
    }
  }


  @Override
  public void onStop() {
    super.onStop();
    if (sketch != null) {
      sketch.onStop();
    }
  }


  public void requestDraw() {
  }


  public boolean canDraw() {
    return true;
  }


  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[],
                                         int[] grantResults) {
    if (sketch != null) {
      sketch.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
  }


  @Override
  public void onNewIntent(Intent intent) {
    if (sketch != null) {
      sketch.onNewIntent(intent);
    }
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (sketch != null) {
      sketch.onActivityResult(requestCode, resultCode, data);
    }
  }


  @Override
  public void onBackPressed() {
    if (sketch != null) {
      sketch.onBackPressed();
    }
  }
}
