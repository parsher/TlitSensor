package mmj.example.tlitsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    init {
        greenPaint.color = Color.GREEN
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        // 일러스트와 같은 방식으로 먼저 그려진 것은 화면 하단에 놓인다.
        canvas?.drawCircle(cX, cY, 100f, blackPaint) // x, y, 반지름, 색

        canvas?.drawCircle(xCoord + cX, yCoord + cY, 100f, greenPaint)

        canvas?.drawLine(cX -20, cY, cX + 20, cY, blackPaint) // x1, y1, x2, y2, 색
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, blackPaint)
        super.onDraw(canvas)
    }

    private var cX: Float = 0f
    private var cY: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f
        super.onSizeChanged(w, h, oldw, oldh)
    }

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    fun onSensorEvent(event: SensorEvent) {
        // event values[0] = x, event values[1] = y
        // 화면을 가로로 표시하기 때문에 x, y축을 바꿔서 전달함
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        // redraw - call onDraw()
        invalidate()
    }

}