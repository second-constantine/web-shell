#!/bin/bash

commit=$*

git pull
git add .
git commit -m "$commit"
git push
