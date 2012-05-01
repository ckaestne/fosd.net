Generator for the fosd.net site
-------------------------------


To add a link to fosd.net send me a mail with the following information
 
 * Title of the page
 * URL of the page
 * Shortcut (fosd.net/shortcut)

Alternatively you can add a link to the `links` file yourself by forking and sending a pull request.


Internals
---------

All links are maintained in the `links` file.

The file has the following format:

   title;link;shortcut

where title is the title of the page that will appear in the link list on fosd.net, link is the url that points to the page, and shortcut is the (case sensitive) abbreviation in the final link. It is possible to add multiple shortcuts separated by semicolons.


