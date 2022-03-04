package `in`.sarangal.broadrecycleradapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom decorator [RecyclerView.ItemDecoration] which allows to
 * add custom spaces/margins around the view
 *
 * @param spacing Pass value for all type of spacing
 * @param horizontalSpacing Spacing for Left and Right Side
 * @param verticalSpacing Spacing for Top and Bottom Side
 * @param orientation Orientation of RecyclerView
 * @param topOrLeft Enable/Disable Spacing for Top (in Vertical Orientation) and
 *                  for Left (in Horizontal Orientation)
 * @param bottomOrRight Enable/Disable Spacing for Bottom (in Vertical Orientation) and
 *                  for Right (in Horizontal Orientation)
 * @param gridSpan Span of Grid in Case of GridLayoutManager
 */
class CustomSpaceDecorator(
    private val spacing: Int = 0,
    private val horizontalSpacing: Int = spacing,
    private val verticalSpacing: Int = spacing,
    private val orientation: Orientation = Orientation.VERTICAL,
    private val topOrLeft: Boolean = true,
    private val bottomOrRight: Boolean = true,
    private val bottomSpacing: Int? = null,
    private val gridSpan: Int = 1
) : RecyclerView.ItemDecoration() {

    enum class Orientation {
        VERTICAL, /* Vertical Scrolling in RecyclerView */
        HORIZONTAL, /* Horizontal Scrolling in RecyclerView */
        VERTICAL_GRID /* Vertical Scrolling with Grid in RecyclerView */
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        var top = 0
        var left = 0
        var right = 0

        when (orientation) {
            Orientation.HORIZONTAL -> {
                if (parent.getChildLayoutPosition(view) == 0) {
                    left = if (topOrLeft) horizontalSpacing else 0
                }
                top = if (topOrLeft) verticalSpacing else 0

                right = if (bottomOrRight) horizontalSpacing else 0
            }

            Orientation.VERTICAL -> {
                left = if (topOrLeft) horizontalSpacing else 0
                if (parent.getChildLayoutPosition(view) == 0) {
                    top = if (topOrLeft) verticalSpacing else 0
                }

                right = if (bottomOrRight) horizontalSpacing else 0
            }

            Orientation.VERTICAL_GRID -> {
                val position = parent.getChildLayoutPosition(view)

                left = if (topOrLeft) (horizontalSpacing / 2) else 0
                right = if (bottomOrRight) (horizontalSpacing / 2) else 0

                parent.setPaddingRelative(
                    horizontalSpacing / 2, parent.paddingTop,
                    horizontalSpacing / 2, parent.paddingBottom
                )

                if (position < gridSpan) { /* All Items of First Row */
                    top = if (topOrLeft) verticalSpacing else 0
                }
            }
        }

        outRect.left = left
        outRect.top = top
        outRect.right = right

        if (bottomSpacing == null) {
            outRect.bottom = if (bottomOrRight) verticalSpacing else 0
        } else {
            val dataSize = state.itemCount
            val position = parent.getChildAdapterPosition(view)
            if (dataSize > 0 && position == dataSize - 1) {
                outRect.bottom = if (bottomOrRight) bottomSpacing else 0
            } else {
                outRect.bottom = if (bottomOrRight) verticalSpacing else 0
            }
        }
    }
}