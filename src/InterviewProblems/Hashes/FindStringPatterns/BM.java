package InterviewProblems.Hashes.FindStringPatterns;

// Boyer and moore algorithm
// - uses other hueristics to skip characters faster
// - bad character + good suffix
//   - this combination eliminates lots of comparisons
// - still need hashes, but it does good to propose how
//     much the array should shift
// - Q: For bad character, do you have to check each character in the
//        pattern to see if it matches the bad one, or is there a pre
//        stored solution that does this more quickly?

// BM = Boyer-Moore
public class BM {
}
