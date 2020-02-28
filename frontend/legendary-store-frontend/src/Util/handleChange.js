export default function(callback) {
    function inner(e) {
        callback(e.target.value);
    }
    return inner;
}