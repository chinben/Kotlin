package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var list = listOf<Lesson>();

    fun updateAndNotify(list: List<Lesson>) {
        this.list = list;
        notifyDataSetChanged();
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent);
    }

    override fun onBindViewHolder(@NonNull holder: LessonViewHolder, position: Int) {
        holder.onBind(list.get(position));
    }


    /**
     * 静态内部类
     */
     class LessonViewHolder : BaseViewHolder {

        constructor(@NonNull itemView: View) : super(itemView);

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_lesson, parent, false));
            }
        }


        fun onBind(lesson: Lesson) {
            var date = lesson.date;
            if (date == null) {
                date = "日期待定";
            }
            setText(R.id.tv_date, date);

            setText(R.id.tv_content, lesson.content);

            val state = lesson.state;
            if (state != null) {
                setText(R.id.tv_state, state.stateName());
                var colorRes = R.color.playback;
                colorRes = when (state) {
                    Lesson.State.PLAYBACK -> R.color.playback;
                    Lesson.State.LIVE -> R.color.live;
                    Lesson.State.WAIT -> R.color.wait;
                }
                val backgroundColor = itemView.context.getColor(colorRes);
                (getView(R.id.tv_state) as TextView).setBackgroundColor(backgroundColor);
            }
        }
    }
}