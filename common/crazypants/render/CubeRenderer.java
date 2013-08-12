package crazypants.render;

import crazypants.vecmath.Vector3d;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public final class CubeRenderer {

  public static final Vector3d[] verts = new Vector3d[8];
  static {
    for (int i = 0; i < verts.length; i++) {
      verts[i] = new Vector3d();
    }
  }

  public static void render(BoundingBox bb, Icon tex) {
    render(bb, tex, null);
  }

  public static void render(BoundingBox bb, Icon tex, VertexTransform xForm) {
    render(bb, tex.getMinU(), tex.getMaxU(), tex.getMinV(), tex.getMaxV(), xForm);
  }
  
  public static void render(BoundingBox bb, float minU, float maxU, float minV, float maxV) {
    render(bb, minU,maxU,minV,maxV,null);
  }

  public static void render(BoundingBox bb, float minU, float maxU, float minV, float maxV, VertexTransform xForm) {
    setupVertices(bb, xForm);

    float tmp = minV;
    minV = maxV;
    maxV = tmp;
    
    Tessellator tessellator = Tessellator.instance;

    tessellator.setNormal(0, 0, -1);

    addVecWithUV(verts[1], minU, minV);
    addVecWithUV(verts[0], maxU, minV);
    addVecWithUV(verts[3], maxU, maxV);
    addVecWithUV(verts[2], minU, maxV);

    tessellator.setNormal(0, 0, 1);

    addVecWithUV(verts[4], minU, minV);
    addVecWithUV(verts[5], maxU, minV);
    addVecWithUV(verts[6], maxU, maxV);
    addVecWithUV(verts[7], minU, maxV);

    tessellator.setNormal(0, 1, 0);

    addVecWithUV(verts[6], minU, minV);
    addVecWithUV(verts[2], minU, maxV);
    addVecWithUV(verts[3], maxU, maxV);
    addVecWithUV(verts[7], maxU, minV);

    tessellator.setNormal(0, -1, 0);

    addVecWithUV(verts[0], maxU, maxV);
    addVecWithUV(verts[1], minU, maxV);
    addVecWithUV(verts[5], minU, minV);
    addVecWithUV(verts[4], maxU, minV);

    tessellator.setNormal(1, 0, 0);
    addVecWithUV(verts[2], minU, maxV);
    addVecWithUV(verts[6], maxU, maxV);
    addVecWithUV(verts[5], maxU, minV);
    addVecWithUV(verts[1], minU, minV);

    tessellator.setNormal(-1, 0, 0);
    addVecWithUV(verts[0], minU, minV);
    addVecWithUV(verts[4], maxU, minV);
    addVecWithUV(verts[7], maxU, maxV);
    addVecWithUV(verts[3], minU, maxV);
  }

  public static void render(BoundingBox bb, Icon[] faceTextures, VertexTransform xForm) {
    setupVertices(bb, xForm);
    float minU;
    float maxU;
    float minV;
    float maxV;
    Icon tex;

    Tessellator tessellator = Tessellator.instance;

    tessellator.setNormal(0, 0, -1);
    tex = faceTextures[0];
    minU = tex.getMinU();
    maxU = tex.getMaxU();
    minV = tex.getMinV();
    maxV = tex.getMaxV();
    addVecWithUV(verts[1], minU, minV);
    addVecWithUV(verts[0], maxU, minV);
    addVecWithUV(verts[3], maxU, maxV);
    addVecWithUV(verts[2], minU, maxV);

    tessellator.setNormal(0, 0, 1);
    tex = faceTextures[1];
    minU = tex.getMinU();
    maxU = tex.getMaxU();
    minV = tex.getMinV();
    maxV = tex.getMaxV();
    addVecWithUV(verts[4], minU, minV);
    addVecWithUV(verts[5], maxU, minV);
    addVecWithUV(verts[6], maxU, maxV);
    addVecWithUV(verts[7], minU, maxV);

    tessellator.setNormal(0, 1, 0);
    tex = faceTextures[2];
    minU = tex.getMinU();
    maxU = tex.getMaxU();
    minV = tex.getMinV();
    maxV = tex.getMaxV();
    addVecWithUV(verts[6], minU, minV);
    addVecWithUV(verts[2], minU, maxV);
    addVecWithUV(verts[3], maxU, maxV);
    addVecWithUV(verts[7], maxU, minV);

    tessellator.setNormal(0, -1, 0);
    tex = faceTextures[3];
    minU = tex.getMinU();
    maxU = tex.getMaxU();
    minV = tex.getMinV();
    maxV = tex.getMaxV();
    addVecWithUV(verts[0], maxU, maxV);
    addVecWithUV(verts[1], minU, maxV);
    addVecWithUV(verts[5], minU, minV);
    addVecWithUV(verts[4], maxU, minV);

    tessellator.setNormal(1, 0, 0);
    tex = faceTextures[4];
    minU = tex.getMinU();
    maxU = tex.getMaxU();
    minV = tex.getMinV();
    maxV = tex.getMaxV();
    addVecWithUV(verts[2], minU, maxV);
    addVecWithUV(verts[6], maxU, maxV);
    addVecWithUV(verts[5], maxU, minV);
    addVecWithUV(verts[1], minU, minV);

    tessellator.setNormal(-1, 0, 0);
    tex = faceTextures[5];
    minU = tex.getMinU();
    maxU = tex.getMaxU();
    minV = tex.getMinV();
    maxV = tex.getMaxV();
    addVecWithUV(verts[0], minU, minV);
    addVecWithUV(verts[4], maxU, minV);
    addVecWithUV(verts[7], maxU, maxV);
    addVecWithUV(verts[3], minU, maxV);
  }

  public static void setupVertices(BoundingBox bound) {
    setupVertices(bound, null);
  }

  public static void setupVertices(BoundingBox bound, VertexTransform xForm) {
    verts[0].set(bound.minX, bound.minY, bound.minZ);
    verts[1].set(bound.maxX, bound.minY, bound.minZ);
    verts[2].set(bound.maxX, bound.maxY, bound.minZ);
    verts[3].set(bound.minX, bound.maxY, bound.minZ);
    verts[4].set(bound.minX, bound.minY, bound.maxZ);
    verts[5].set(bound.maxX, bound.minY, bound.maxZ);
    verts[6].set(bound.maxX, bound.maxY, bound.maxZ);
    verts[7].set(bound.minX, bound.maxY, bound.maxZ);

    if (xForm != null) {
      for (Vector3d vec : verts) {
        xForm.apply(vec);
      }
    }
  }

  public static void addVecWithUV(Vector3d vec, double u, double v) {
    Tessellator.instance.addVertexWithUV(vec.x, vec.y, vec.z, u, v);
  }

  private CubeRenderer() {
  }

}
