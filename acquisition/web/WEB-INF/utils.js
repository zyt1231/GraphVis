/**
 * Created by Ting on 5/5/17.
 */
function sortOnValues(dict) {
    var items = Object.keys(dict).map(function (key) {
        return [key, dict[key]];
    });
    // Sort the array based on the second element
    items.sort(function (first, second) {
        return second[1] - first[1];
    });

// Create a new array with only the first 5 items
    console.log(items.slice(0, 10));
    return items.slice(0, 10);
}
