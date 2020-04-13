package core.workers.javaScriptExecutor;

public class ScriptsJS {
    //<editor-fold desc="dragDropScript">
    private static final String dragDropScript ="var DndSimulatorDataTransfer = function() {\n" +
            "this.data = {};\n" +
            "this.types = [];\n" +
            "};\n" +
            "\n" +
            "DndSimulatorDataTransfer.prototype.dropEffect = \"move\";\n" +
            "DndSimulatorDataTransfer.prototype.effectAllowed = \"all\";\n" +
            "DndSimulatorDataTransfer.prototype.files = [];\n" +
            "DndSimulatorDataTransfer.prototype.items = [];\n" +
            "DndSimulatorDataTransfer.prototype.clearData = function(format) {\n" +
            "if(format) {\n" +
            "delete this.data[format];\n" +
            "\n" +
            "var index = this.types.indexOf(format);\n" +
            "delete this.types[index];\n" +
            "delete this.data[index];\n" +
            "} else {\n" +
            "this.data = {};\n" +
            "}\n" +
            "};\n" +
            "DndSimulatorDataTransfer.prototype.setData = function(format, data) {\n" +
            "this.data[format] = data;\n" +
            "this.items.push(data);\n" +
            "this.types.push(format);\n" +
            "};\n" +
            "DndSimulatorDataTransfer.prototype.getData = function(format) {\n" +
            "if(format in this.data) {\n" +
            "return this.data[format];\n" +
            "}\n" +
            "\n" +
            "return \"\";\n" +
            "};\n" +
            "DndSimulatorDataTransfer.prototype.setDragImage = function(img, xOffset, yOffset) {\n" +
            "/* since simulation doesn\"t replicate the visual effects, there is\n" +
            "no point in implementing this */\n" +
            "};\n" +
            "\n" +
            "DndSimulator = {\n" +
            "simulate: function(sourceElement, targetElement, transferData) {\n" +
            "/ if strings are specified, assume they are CSS selectors /\n" +
            "if(typeof sourceElement == \"string\") {\n" +
            "sourceElement = document.querySelector(sourceElement);\n" +
            "}\n" +
            "\n" +
            "if(typeof targetElement == \"string\") {\n" +
            "targetElement = document.querySelector(targetElement);\n" +
            "}\n" +
            "\n" +
            "/* get the coordinates of both elements, note that\n" +
            "left refers to X, and top to Y */\n" +
            "var sourceCoordinates = sourceElement.getBoundingClientRect();\n" +
            "var targetCoordinates = targetElement.getBoundingClientRect();\n" +
            "\n" +
            "/* simulate a mouse down event on the coordinates\n" +
            "of the source element */\n" +
            "var mouseDownEvent = this.createEvent(\n" +
            "\"mousedown\",\n" +
            "{\n" +
            "clientX: sourceCoordinates.left,\n" +
            "clientY: sourceCoordinates.top\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "sourceElement.dispatchEvent(mouseDownEvent);\n" +
            "var dataTransfer = new DndSimulatorDataTransfer();\n" +
            "dataTransfer.setData(\"application/x-dnd\", transferData);\n" +
            "/ simulate a drag start event on the source element /\n" +
            "var dragStartEvent = this.createEvent(\n" +
            "\"dragstart\",\n" +
            "{\n" +
            "clientX: sourceCoordinates.left,\n" +
            "clientY: sourceCoordinates.top,\n" +
            "dataTransfer: dataTransfer\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "sourceElement.dispatchEvent(dragStartEvent);\n" +
            "\n" +
            "/ simulate a drag event on the source element /\n" +
            "var dragEvent = this.createEvent(\n" +
            "\"drag\",\n" +
            "{\n" +
            "clientX: sourceCoordinates.left,\n" +
            "clientY: sourceCoordinates.top\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "sourceElement.dispatchEvent(dragEvent);\n" +
            "\n" +
            "/ simulate a drag enter event on the target element /\n" +
            "var dragEnterEvent = this.createEvent(\n" +
            "\"dragenter\",\n" +
            "{\n" +
            "clientX: targetCoordinates.left,\n" +
            "clientY: targetCoordinates.top,\n" +
            "dataTransfer: dragStartEvent.dataTransfer\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "targetElement.dispatchEvent(dragEnterEvent);\n" +
            "\n" +
            "/ simulate a drag over event on the target element /\n" +
            "var dragOverEvent = this.createEvent(\n" +
            "\"dragover\",\n" +
            "{\n" +
            "clientX: targetCoordinates.left,\n" +
            "clientY: targetCoordinates.top,\n" +
            "dataTransfer: dragStartEvent.dataTransfer\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "targetElement.dispatchEvent(dragOverEvent);\n" +
            "\n" +
            "/ simulate a drop event on the target element /\n" +
            "var dropEvent = this.createEvent(\n" +
            "\"drop\",\n" +
            "{\n" +
            "clientX: targetCoordinates.left,\n" +
            "clientY: targetCoordinates.top,\n" +
            "dataTransfer: dragStartEvent.dataTransfer\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "targetElement.dispatchEvent(dropEvent);\n" +
            "\n" +
            "/ simulate a drag end event on the source element /\n" +
            "var dragEndEvent = this.createEvent(\n" +
            "\"dragend\",\n" +
            "{\n" +
            "clientX: targetCoordinates.left,\n" +
            "clientY: targetCoordinates.top,\n" +
            "dataTransfer: dragStartEvent.dataTransfer\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "sourceElement.dispatchEvent(dragEndEvent);\n" +
            "\n" +
            "/ simulate a mouseup event on the target element /\n" +
            "var mouseUpEvent = this.createEvent(\n" +
            "\"mouseup\",\n" +
            "{\n" +
            "clientX: targetCoordinates.left,\n" +
            "clientY: targetCoordinates.top\n" +
            "}\n" +
            ");\n" +
            "\n" +
            "targetElement.dispatchEvent(mouseUpEvent);\n" +
            "},\n" +
            "\n" +
            "createEvent: function(eventName, options) {\n" +
            "var event = document.createEvent(\"CustomEvent\");\n" +
            "event.initCustomEvent(eventName, true, true, null);\n" +
            "\n" +
            "event.view = window;\n" +
            "event.detail = 0;\n" +
            "event.ctlrKey = false;\n" +
            "event.altKey = false;\n" +
            "event.shiftKey = false;\n" +
            "event.metaKey = false;\n" +
            "event.button = 0;\n" +
            "event.relatedTarget = null;\n" +
            "\n" +
            "/* if the clientX and clientY options are specified,\n" +
            "also calculated the desired screenX and screenY values */\n" +
            "if(options.clientX && options.clientY) {\n" +
            "event.screenX = window.screenX + options.clientX;\n" +
            "event.screenY = window.screenY + options.clientY;\n" +
            "}\n" +
            "\n" +
            "/* copy the rest of the options into\n" +
            "the event object */\n" +
            "for (var prop in options) {\n" +
            "event[prop] = options[prop];\n" +
            "}\n" +
            "\n" +
            "return event;\n" +
            "}\n" +
            "};\n" +
            "\n" +
            "\n" +
            "//from = document.querySelectorAll(\"section.list-group-dragAndDrop\")[1]\n" +
            "//to = document.querySelectorAll(\"section.list-group-dragAndDrop\")[0]\n" +
            "//fromItem = from.querySelector(\".list-item\")\n" +
            "\n" +
            "//var transferData = {\"data\":{\"accountId\":792906,\"displayName\":\"asdd asd Account 906144a26\"}};\n" +
            "\n" +
            "DndSimulator.simulate(arguments[0], arguments[1]);";
    //</editor-fold>

    protected static String getDragDropScript(){
        return dragDropScript;
    }

}
