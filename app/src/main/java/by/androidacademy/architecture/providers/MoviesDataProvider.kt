package by.androidacademy.architecture.providers;

import by.androidacademy.architecture.R

class MoviesDataProvider {

    companion object {
        val TYPE_TITLE = 1;
        val TYPE_DESCRIPTION = 2;
        val TYPE_IMAGE = 3;
        val TYPE_IMAGE_URL = 4;

        fun getResource(type: Int, position: Int): Int {
            return if (type == TYPE_TITLE) {
                when (position) {
                    0 -> R.string.txt_title_2;
                    1 -> R.string.txt_title_3;
                    2 -> R.string.txt_title_4;
                    3 -> R.string.txt_title_5;
                    4 -> R.string.txt_title_6;
                    5 -> R.string.txt_title_7;
                    6 -> R.string.txt_title_8;
                    7 -> R.string.txt_title_9;
                    else -> R.string.txt_title_10;
                }
            } else {
                if (type == TYPE_DESCRIPTION) {
                    when (position) {
                        0 -> R.string.txt_description_2;
                        1 -> R.string.txt_description_3;
                        2 -> R.string.txt_description_4;
                        3 -> R.string.txt_description_5;
                        4 -> R.string.txt_description_6;
                        5 -> R.string.txt_description_7;
                        6 -> R.string.txt_description_8;
                        7 -> R.string.txt_description_9;
                        else -> R.string.txt_description_10;
                    }
                } else {
                    if (type == TYPE_IMAGE) {
                        when (position) {
                            0 -> R.drawable.img_demo_2;
                            1 -> R.drawable.img_demo_3;
                            2 -> R.drawable.img_demo_4;
                            3 -> R.drawable.img_demo_5;
                            4 -> R.drawable.img_demo_6;
                            5 -> R.drawable.img_demo_7;
                            6 -> R.drawable.img_demo_8;
                            7 -> R.drawable.img_demo_9;
                            else -> R.drawable.img_demo_10;
                        }
                    } else {
                        when (position) {
                            0 -> R.string.txt_url_2;
                            1 -> R.string.txt_url_3;
                            2 -> R.string.txt_url_4;
                            3 -> R.string.txt_url_5;
                            4 -> R.string.txt_url_6;
                            5 -> R.string.txt_url_7;
                            6 -> R.string.txt_url_8;
                            7 -> R.string.txt_url_9;
                            else -> R.string.txt_url_10;
                        }
                    }
                }
            }
        }

        fun getSize(): Int {
            return 9
        }
    }
}
