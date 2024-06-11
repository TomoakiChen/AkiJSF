class AkiClipboard {

    targetSelector;
    targetElement;
    triggerSelector;
    triggerElement;

    constructor(targetSelector, triggerSelector) {
        this.targetSelector = targetSelector;
        this.triggerSelector = triggerSelector;
        this.doSetupTargetElement();
        this.doSetupTriggerElement();
        this.doBindClipboardEvent();
    }

    doSetupTargetElement() {
        this.targetElement = document.querySelector(this.targetSelector);
        // console.log(typeof this.#targetSelector);
        // console.log(typeof this.#targetElement);
    }

    doSetupTriggerElement() {
        if (this.triggerSelector !== undefined) {
            this.triggerElement = document.querySelector(this.triggerSelector);
        } else {
            this.triggerElement = document.querySelector(this.targetSelector); //沒有指定 triggerEleemnt 時，自身 Element 視為 trigger
        }
        // console.log(typeof this.#triggerElement);
    }

    doBindClipboardEvent() {
        let obj = this;
        this.triggerElement.addEventListener('click', function () {
            obj.doCopy2Clipboard();
        });
    }

    getTargetElement() {
        return this.targetElement;
    }

    getTriggerElement() {
        return this.triggerElement;
    }

    doCopy2Clipboard() {
        var nodeName = this.targetElement.nodeName;
        if (nodeName == 'INPUT') {
            this.targetElement.select();
            this.targetElement.setSelectionRange(0, 99999); /* For mobile devices */
        }
        navigator.clipboard.writeText(HtmlElementHelper.obtainValue(this.targetElement));
    }
}

// ----------------------------------------------------------------------------------------
var HtmlElementHelper = {
    obtainValue: function (htmlElement) {
        var nodeName = htmlElement.nodeName;
        if (nodeName == 'INPUT') {
            return htmlElement.value;
        } else {
            return htmlElement.innerText;
        }
    }
}
