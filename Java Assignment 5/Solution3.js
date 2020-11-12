var bracketMatching = function (str) 
{

    var stack = []; 
    for (let i = 0; i < str.length; i++) 
    {
        if (str[i] === '(' || str[i] === '{' || str[i] === '[' ) 
        {
            stack.push(str[i]);
        }
        else 
        {
            let last = stack.pop();
            if ((str[i] === ')' && last!= '(') || (str[i] === ']' && last!= '[') || (str[i] === '}' && last!= '{')) 
            {
                return false;
            }
        }
    }
    if (stack.length !== 0) 
    {
        return false;
    }
    else{
    return true;}
}


var inputString = prompt("Enter any string containing {,(,[,],)or }. Do not enter spaces or commas.");

console.log(bracketMatching(inputString));