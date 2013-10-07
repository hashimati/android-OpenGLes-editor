package com.editor.common;

import android.graphics.*;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;




import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: walker
 * Date: 13-8-9
 * To change this template use File | Settings | File Templates.
 */
public class TextureBuilder {



    public static int getTexture(GLSurfaceView arg_myView, int arg_drawableId) {
        // ��������ID
        int[] textures = new int[1];
        GLES20.glGenTextures(1, // ����������id������
                textures, // ����id������
                0 // ƫ����
        );
        int textureId = textures[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_REPEAT);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_REPEAT);

        // ͨ������������ͼƬ
        InputStream is = arg_myView.getResources().openRawResource(arg_drawableId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ʵ�ʼ�������
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, // �������ͣ���OpenGL
                // ES�б���ΪGL10.GL_TEXTURE_2D
                0, // �����Ĳ�Σ�0��ʾ����ͼ��㣬��������Ϊֱ����ͼ
                bitmapTmp, // ����ͼ��
                0 // �����߿�ߴ�
        );
        bitmapTmp.recycle(); // �������سɹ����ͷ�ͼƬ
        return textureId;
    }



}