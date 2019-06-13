public class ActionType
{
    public static final String Search = "search";
    public static final String Insert = "insert";
    public static final String Delete = "delete";
    public static final String Draw = "draw";

    public static String[] values()
    {
        return new String[]
        {
            Search,
            Insert,
            Delete,
            Draw
        };
    }
}