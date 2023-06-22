package metric.Model

enum class Metric(val shortName: String, val fullName: String, val description: String) {
    LINES("LINES", "Lines of Code", ""),
    ARGUMENTS("ARGUMENTS", "Number of Arguments", ""),
    LOCAL_VARIABLES("LOCAL_VARIABLES", "Number of Local Variables", ""),
    FUNCTION_CALLS("FUNCTION_CALLS", "Number of Function Calls", ""),
    CONDITIONAL_STATEMENTS("CONDITIONAL_STATEMENTS", "Number of Conditional Statements", ""),
    ITERATION_STATEMENTS("ITERATION_STATEMENTS", "Number of Iteration Statements", ""),
    RETURN_STATEMENTS("RETURN_STATEMENTS", "Number of Return Statements", ""),
    INPUT_STATEMENTS("INPUT_STATEMENTS", "Number of Input Statements", ""),
    OUTPUT_STATEMENTS("OUTPUT_STATEMENTS", "Number of Output Statements", ""),
    FUNCTION_ASSIGNMENTS("FUNCTION_ASSIGNMENTS", "Number of Assignments Through Function Calls", ""),
    SELECTION_STATEMENTS("SELECTION_STATEMENTS", "Number of Selection Statements", ""),
    ASSIGNMENT_STATEMENTS("ASSIGNMENT_STATEMENTS", "Number of Assignments", "");
}