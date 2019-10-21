package kz.movieapp.moviedb.customview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kz.movieapp.moviedb.R
import kotlin.random.Random


class GenreItemView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var colors = listOf(
        0xff40D20D.toInt(),
        0xffB155FA.toInt(),
        0xffEB930F.toInt(),
        0xff1833BF.toInt(),
        0xffFA555F.toInt(),
        0xffAF17BC.toInt()
    )
    var back_color = 0
    var text = ""
    var paint: Paint
    var paint_text: Paint

    init {

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.GenreItemView, 0, 0)
        try {
            back_color = a.getInteger(R.styleable.GenreItemView_back_color, colors[Random.nextInt(0, 6)])
            text = a.getString(R.styleable.GenreItemView_text)!!
        } finally {
            a.recycle()
        }
        paint = Paint()
        paint.color = back_color

        paint_text = Paint()
        paint_text.color = Color.WHITE
        paint_text.textSize = 45f
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val path = roundedRect(
            20f, 20f, 450f, 760f, 20f, 20f,
            true, true, true, true
        )
        canvas?.drawPath(path, paint)
        canvas?.drawText(text, 100f, 200f, paint_text)

    }

    private fun roundedRect(
        left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float,
        tl: Boolean, tr: Boolean, br: Boolean, bl: Boolean
    ): Path {
        var rx = rx
        var ry = ry
        val path = Path()
        if (rx < 0) rx = 0f
        if (ry < 0) ry = 0f
        val width = right - left
        val height = bottom - top
        if (rx > width / 2) rx = width / 2
        if (ry > height / 2) ry = height / 2
        val widthMinusCorners = width - 2 * rx
        val heightMinusCorners = height - 2 * ry

        path.moveTo(right, top + ry)
        if (tr)
            path.rQuadTo(0f, -ry, -rx, -ry)//top-right corner
        else {
            path.rLineTo(0f, -ry)
            path.rLineTo(-rx, 0f)
        }
        path.rLineTo(-widthMinusCorners, 0f)
        if (tl)
            path.rQuadTo(-rx, 0f, -rx, ry) //top-left corner
        else {
            path.rLineTo(-rx, 0f)
            path.rLineTo(0f, ry)
        }
        path.rLineTo(0f, heightMinusCorners)

        if (bl)
            path.rQuadTo(0f, ry, rx, ry)//bottom-left corner
        else {
            path.rLineTo(0f, ry)
            path.rLineTo(rx, 0f)
        }

        path.rLineTo(widthMinusCorners, 0f)
        if (br)
            path.rQuadTo(rx, 0f, rx, -ry) //bottom-right corner
        else {
            path.rLineTo(rx, 0f)
            path.rLineTo(0f, -ry)
        }

        path.rLineTo(0f, -heightMinusCorners)

        path.close()

        return path
    }
}