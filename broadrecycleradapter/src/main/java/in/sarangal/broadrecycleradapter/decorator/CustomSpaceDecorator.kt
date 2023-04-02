package `in`.sarangal.broadrecycleradapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom decorator [RecyclerView.ItemDecoration] which allows to
 * add custom spaces/margins around the view
 */
class CustomSpaceDecorator private constructor(builder: Builder) : RecyclerView.ItemDecoration() {

    private val horizontalSpacing: Int = builder.horizontalSpacing
    private val verticalSpacing: Int = builder.verticalSpacing
    private val orientation: Orientation = builder.orientation
    private val topOrLeft: Boolean = builder.topOrLeft
    private val bottomOrRight: Boolean = builder.bottomOrRight
    private val bottomSpacing: Int? = builder.bottomSpacing
    private val gridSpan: Int = builder.gridSpan

    enum class Orientation {
        VERTICAL, /* Vertical Scrolling in RecyclerView */
        HORIZONTAL, /* Horizontal Scrolling in RecyclerView */
        VERTICAL_GRID /* Vertical Scrolling with Grid in RecyclerView */
    }

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     *
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     *
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
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

    /**
     * Builder Class Pattern to Implement Custom Space Decorator
     * */
    class Builder {
        internal var horizontalSpacing = 0
        internal var verticalSpacing = 0
        internal var orientation = Orientation.VERTICAL
        internal var topOrLeft = true
        internal var bottomOrRight = true
        internal var bottomSpacing: Int? = null
        internal var gridSpan = 1

        /**
         * @param spacing Pass value for all type of spacing
         * */
        fun setSpacing(spacing: Int): Builder = apply {
            this.horizontalSpacing = spacing
            this.verticalSpacing = spacing
        }

        /**
         * @param horizontalSpacing Spacing for Left and Right Side
         * */
        fun setHorizontalSpacing(horizontalSpacing: Int): Builder =
            apply { this.horizontalSpacing = horizontalSpacing }

        /**
         * @param verticalSpacing Spacing for Top and Bottom Side
         * */
        fun setVerticalSpacing(verticalSpacing: Int): Builder =
            apply { this.verticalSpacing = verticalSpacing }

        /**
         * @param orientation Orientation of RecyclerView
         * */
        fun setOrientation(orientation: Orientation): Builder =
            apply { this.orientation = orientation }

        /**
         * @param topOrLeft Enable/Disable Spacing for Top (in Vertical Orientation) and
         *                  for Left (in Horizontal Orientation)
         * */
        fun setTopOrLeft(topOrLeft: Boolean): Builder = apply { this.topOrLeft = topOrLeft }

        /**
         * @param bottomOrRight Enable/Disable Spacing for Bottom (in Vertical Orientation) and
         *                  for Right (in Horizontal Orientation)
         * */
        fun setBottomOrRight(bottomOrRight: Boolean): Builder =
            apply { this.bottomOrRight = bottomOrRight }

        /**
         * @param bottomSpacing Spacing for Bottom Side
         * */
        fun setBottomSpacing(bottomSpacing: Int?): Builder =
            apply { this.bottomSpacing = bottomSpacing }

        /**
         * @param gridSpan Span of Grid in Case of GridLayoutManager
         * */
        fun setGridSpan(gridSpan: Int): Builder = apply { this.gridSpan = gridSpan }

        /**
         * Implement CustomSpaceDecorator Constructor
         * */
        fun build(): CustomSpaceDecorator {
            return CustomSpaceDecorator(this)
        }
    }
}