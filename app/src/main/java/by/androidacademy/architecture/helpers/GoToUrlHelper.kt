package by.androidacademy.architecture.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import by.androidacademy.architecture.R


class GoToUrlHelper {

    companion object {
        fun gotoUrl(ctx: Context) {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6ZfuNTqbHE8"))
            val packageManager = ctx.getPackageManager()
            if (intent.resolveActivity(packageManager) != null) {
                ctx.startActivity(intent)
            } else {
                Toast.makeText(ctx, R.string.toast_no_intent, Toast.LENGTH_SHORT).show()
            }
        }

        fun gotoUrl(ctx: Context, res: Int) {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(ctx.getString(res)))
            val packageManager = ctx.getPackageManager()
            if (intent.resolveActivity(packageManager) != null) {
                ctx.startActivity(intent)
            } else {
                Toast.makeText(ctx, R.string.toast_no_intent, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
