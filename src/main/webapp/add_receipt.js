
// let reimb_item = document.createElement(fieldset);


let container = document.getElementById("fields");
let receiptCount = 1;
let receiptCountString = receiptCount;

function stringName()
{
    // ideally limit to 99 receipts or less
    if(receiptCount<10){
        reciptCountString = "0" + receiptCount;
    }else{
        receiptCountString = "" + receiptCount;
    };
}


// TODO: remove fieldsets that are empty rather than delete last one.
// TODO: don't add one more if last fieldset is empty
// TODO: What to do if a feildset is empty?
function onemore()
{
    stringName();

    let newreceiptimage = document.createElement("input");
    newreceiptimage.type="file";
    newreceiptimage.name=`receipt${reciptCountString}image`;
    newreceiptimage.accept="image/gif, image/jpg, image/jpeg, image/png";
    
    let newreceiptamount = document.createElement("input");
    newreceiptamount.type="number";
    newreceiptamount.name=`receipt${receiptCountString}amount`;
    newreceiptamount.step=".01";
    newreceiptamount.min="0";
    newreceiptamount.pattern="^\d*(\.\d{0,2})?$";
   
    container.appendChild(newreceiptimage);
    container.appendChild(newreceiptamount);
    receiptCount++;
};

function oneless(){
    //stringName();
    if(receiptCount>1){
        // TODO: this feels like cheating... do something like add div / remove div group
        container.removeChild(container.lastChild);
        container.removeChild(container.lastChild);
        receiptCount--;
    }
}

// if (add !== undefined) {
//     // Now we know that foo is defined, we are good to go.
// }

onemore();
document.getElementById("add").addEventListener("click",onemore);

document.getElementById("subtract").addEventListener("click",oneless);

// container.appendChild();

// while (fields.hasChildNodes()) {
//     container.removeChild(container.lastChild);
// }

/* <fieldset>

<p>receipt amount</p>
<input type="file" name="receipt01image" accept="image/gif, image/jpg, image/jpeg, image/png" />
<input type="number" name="receipt01amount" step=".01" pattern="^\d*(\.\d{0,2})?$" min="0" /> <br />

<input type="button" name="cleardelete" >delete/clear this</input><br />
<input type="button" name="addAnother">add another</input><br />
<br />
<input id="submit" type="button" name="submit">sumbit these</input>
</fieldset>
 */


//        <!-- multiple="multiple" ? -->
//        <!-- TODO: Turn into JavaScript -->
//        <!-- https://stackoverflow.com/questions/34057595/allow-2-decimal-places-in-input-type-number -->

