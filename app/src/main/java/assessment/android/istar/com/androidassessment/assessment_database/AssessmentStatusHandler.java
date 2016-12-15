package assessment.android.istar.com.androidassessment.assessment_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import assessment.android.istar.com.androidassessment.assessment_pojo.AssessmentStatus;

/**
 * Created by ajith on 13-12-2016.
 */

public class AssessmentStatusHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "talentify_assessment_status";
    private static final String TABLE = "assessment_status";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "data";
    private static final String KEY_STATUS = "status";
    private static final String KEY_SLIDE_POINTER = "slide";
    private static final String EXTERNALSTORAGE = getPath();


    public AssessmentStatusHandler(Context context) {
        super(context, EXTERNALSTORAGE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTENT_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_STATUS + " TEXT," + KEY_SLIDE_POINTER + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void saveContent(String id, String content, String status, String last_pointer) {

        Cursor cursor = getData(Integer.parseInt(id));
        if (cursor != null && cursor.getCount() > 0) {
            System.out.println("updateContent done");
            updateContent(id, content, status, last_pointer);
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, id);
            contentValues.put(KEY_NAME, content);
            contentValues.put(KEY_STATUS, status);
            contentValues.put(KEY_SLIDE_POINTER, last_pointer);
            db.insert(TABLE, null, contentValues);
            System.out.println("saveContent done");
        }
    }

    public void updateContent(String id, String content, String status, String last_pointer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, content);
        contentValues.put(KEY_STATUS, status);
        contentValues.put(KEY_SLIDE_POINTER, last_pointer);
        db.update(TABLE, contentValues, "id = ? ", new String[]{id});
        System.out.println("updateContent done");

    }

    public Integer deleteContent(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("delete content done");
        return db.delete(TABLE,
                "id = ? ",
                new String[]{Integer.toString(id)});

    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE + " where id=" + id + "", null);
        System.out.println("get content done");
        return res;
    }


    public List<AssessmentStatus> getAllContent() {
        List<AssessmentStatus> assessmentStatuses = new ArrayList<AssessmentStatus>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                assessmentStatuses.add(new AssessmentStatus(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return assessmentStatuses;
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    private static String getPath() {

        String path = DATABASE_NAME;
        try {
            if (isExternalStorageReadable()) {
                path = Environment.getExternalStorageDirectory() + ""
                        + File.separator + "Talentify"
                        + File.separator + DATABASE_NAME;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
