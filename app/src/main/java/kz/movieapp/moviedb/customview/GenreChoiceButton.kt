package kz.movieapp.moviedb.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kz.movieapp.moviedb.R
import android.graphics.Typeface

class GenreChoiceButton(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var headerColor = 0xff00574B.toInt()
    var title = "Жанры"
    var genreText = "Выбрать"
    lateinit var paintHeader: Paint
    lateinit var paintGenre: Paint
    lateinit var paint: Paint

    constructor(context: Context, attrs: AttributeSet?, str: String) : this(context, attrs){
        genreText = str
        initializePaintsChangedText()
    }

    private fun initializePaintsChangedText() {
        paint = Paint()
        paint.color = Color.WHITE

        paintHeader = Paint()
        paintHeader.color = headerColor
        paintHeader.textSize = 38f
        val typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        paintHeader.typeface = typeface

        paintGenre = Paint()
        paintGenre.color = Color.BLACK
        val typeface2 = Typeface.create(Typeface.SERIF, Typeface.NORMAL)
        paintGenre.typeface = typeface2
        paintGenre.textSize = 50f
    }

    init {
        initializePaints(attrs)
    }

    private fun initializePaints(attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.GenreChoiceButton, 0, 0)
        try {
            title = a.getString(R.styleable.GenreChoiceButton_title)!!
            genreText = a.getString(R.styleable.GenreChoiceButton_genres)!!
        } finally {
            a.recycle()
        }

        paint = Paint()
        paint.color = Color.WHITE

        paintHeader = Paint()
        paintHeader.color = headerColor
        paintHeader.textSize = 38f
        val typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        paintHeader.typeface = typeface

        paintGenre = Paint()
        paintGenre.color = Color.BLACK
        val typeface2 = Typeface.create(Typeface.SERIF, Typeface.NORMAL)
        paintGenre.typeface = typeface2
        paintGenre.textSize = 50f

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(0f, 0f, 300f, 150f, paint)
        canvas?.drawText(title, 0f, 40f, paintHeader)
        canvas?.drawText(genreText, 0f, 110f, paintGenre)
    }
}