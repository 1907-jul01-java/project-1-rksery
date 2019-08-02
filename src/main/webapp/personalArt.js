let artworks = [];
let artworksElement = document.getElementById("artworks");

function ListArtworks(artworks) {
    return `<table>
                <tr>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Thumbnail</th>
                    <th>Approval</th>
                </tr>
                ${artworks.map(ListArtwork).join('')}
            </table>`;
}
{/* <td>${artwork.artid}</td> */ }
{/* <td>${artwork.approved}</td> */ }
function ListArtwork(artwork) {
    return `<tr>
                
                <td>${artwork.title}</td>
                <td>$${artwork.price}</td>
                <td><a href="/project1/assets/artwork/${artwork.filepath}" target="_blank">
                <img height=3% src="/project1/assets/artwork/${artwork.filepath}"></a></td>
                <td>${artwork.approved}</td>
            </tr>`;
}

// function approve(path) {
//     let xhr = new XMLHttpRequest();
//     let url = "http://localhost:8080/project1/api/artwork/approve";
//     let params = path;
//     let fullpath = url + "?filepath=" + params;
//     console.log(fullpath);
//     xhr.open("put", fullpath);
//     xhr.send();
//     document.location.reload();
// }
// function deny(path) {
//     let xhr = new XMLHttpRequest();
//     let url = "http://localhost:8080/project1/api/artwork/deny";
//     let params = path;
//     xhr.open('put', url + "?filepath=" + params);
//     xhr.send();
//     document.location.reload();
// }

function updateArtworksElement() {
    artworksElement.innerHTML = ListArtworks(artworks);
}

function getArtworks() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/project1/api/artwork/getown";
    let params = sessionStorage.getItem("username");
    let fullpath = url + "?username=" + params;
    xhr.open('get', fullpath);
    xhr.onload = function () {
        artworks = JSON.parse(xhr.responseText);
        updateArtworksElement();
    };
    xhr.send();
}
// function login() {
//     sessionStorage.setItem("username", document.getElementById('username').value);
// }