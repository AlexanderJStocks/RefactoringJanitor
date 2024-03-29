# Java Janitor
Java Janitor is a Java code quality tool that automates code refactoring, enforces coding standards, and optimizes code. It is designed to help developers and teams produce high-quality code, reduce technical debt, and improve code maintainability.

## Features
Java Janitor provides the following features:

Automatic refactoring of code
Enforcement of coding standards and best practices
Optimization of code
Reporting of code quality metric
Integration with popular development tools

## Getting Started
To set up this project as your App, start by creating a GitHub App. Head to GitHub, and under
your profile’s Developer settings, select GitHub Apps, followed by New GitHub App. Fill in the
necessary details and download your App’s private key.
Next, locate your App ID within the ’About’ section on your App’s settings page. Following
this, in the project’s config file, update the existing GitHub App ID and the private key path using
your new App ID and the location of your downloaded private key.
In the GitHubUtils file, adjust the project path to match your local system’s project path. Then,
expose a public URL for webhook communication. Here, services such as ngrok or localtunnel can
come in handy. Alternatively, consider setting up a reverse proxy with tools like Nginx. Remember
to substitute the Webhook URL in your GitHub App settings with the URL generated by your
chosen method.
With these in place, return to your GitHub App settings, select the repositories you intend to
add under the Install App section, and hit Install. Upon completion, the project should trigger
for the first time. Henceforth, any changes within your App-installed repositories will activate the
webhooks, setting your app to process the incoming events.

Contributing
Contributions to Java Janitor are welcome! If you have an idea for a new feature, a bug report, or a pull request, please submit it to the GitHub repository.

License
Java Janitor is released under the MIT License. See LICENSE for details.
